package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.BookRepository;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.platform.exceptions.NotFoundException;
import com.belhard.bookstore.platform.exceptions.NotUpdateException;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    public static final int ISBN_LENGTH = 13;
    private final BookRepository bookRepository;
    private final ConverterService converter;

    @Override
    public List<BookDto> getAll(Pageable pageable) {
        log.info("Received a list of books from BookRepository");
        return bookRepository.findAllAvailable(pageable)
                .stream()
                .map(converter::toBookDto)
                .toList();
    }

    @Override
    public BookDto getById(Long id) {
        log.info("The BookRepository interface method was called to search");
        Book book = bookRepository.findAvailableById(id)
                .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found"));
        return converter.toBookDto(book);
    }

    private void validate(BookDto book) {
        if (book.getIsbn().length() > ISBN_LENGTH) {
            log.error("Isbn parameter value is invalid");
            throw new NotUpdateException("ISBN number cannot be longer than 13 characters.");
        }
        LocalDate date = LocalDate.now();
        if (book.getPublishingYear() < 0 || book.getPublishingYear() > date.getYear()) {
            log.error("Invalid publication year value");
            throw new NotUpdateException("Incorrect year of publication of the book entered.");
        }
        if (book.getPrice().signum() <= 0) {
            log.error("Invalid price value");
            throw new NotUpdateException("Incorrect price of the book entered.");
        }
        log.info("Parameters have been successfully validated");
    }

    @Override
    public BookDto save(BookDto book) {
        validate(book);
        bookRepository.save(converter.toBookEntity(book));
        Book saved = bookRepository.findByIsbn(book.getIsbn())
                .orElseThrow(() -> new NotUpdateException("Book: " + book + " not update"));
        log.info("Book: " + book + " was save");
        return converter.toBookDto(saved);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findAvailableById(id)
                .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found"));
        book.setDeleted(true);
        bookRepository.save(book);
        log.info("Book with id {} deleted", id);
    }

    @Override
    public List<BookDto> getByAuthor(String author, Pageable pageable) {
        log.info("Received a list of books by author from BookRepository");
        List<BookDto> books = bookRepository.findByAuthor(author, pageable)
                .stream()
                .map(converter::toBookDto)
                .toList();
        if(!books.isEmpty()) {
            return books;
        } else {
            throw new NotFoundException("Books by author " + author + " not found!");
        }
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        log.info("The BookRepository method was called to search by ISBN");
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book with ISBN " + isbn + " not found"));
        return converter.toBookDto(book);
    }

    public BigDecimal sumPriceByAuthor (String author, Pageable pageable) {
        log.info("Calculation of the cost of all books of the author");
        return bookRepository.findByAuthor(author, pageable)
                .stream()
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getTotalPages(Integer pageSize) {
        log.info("The method for calculating the number of pages is called");
        return (long) (bookRepository.countAllByDeletedFalse() / pageSize);
    }

    public Long getTotalPagesAuthor(Integer pageSize, String author) {
        log.info("The method for calculating the number of pages is called");
        return (long) (bookRepository.countByAuthor(author) / pageSize);
    }
}
