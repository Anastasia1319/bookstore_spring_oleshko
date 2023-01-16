package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.BookRepository;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import com.belhard.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    public static final int ISBN_LENGTH = 13;
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        log.info("Received a list of books from BookRepository");
        return bookRepository.findAllAvailable()
                .stream()
                .sorted(Comparator.comparing(Book::getId))
                .toList();
    }

    @Override
    public Book getById(Long id) {
        log.info("The BookRepository interface method was called to search");
        return bookRepository.findAvailableById(id)
                .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found"));
    }

    private void validate(Book book) {
        if (book.getIsbn().length() > ISBN_LENGTH) {
            log.error("Isbn parameter value is invalid");
            throw new NotUpdateException("ISBN number cannot be longer than 13 characters.");
        }
        LocalDate date = LocalDate.now();
        if (book.getPublishinYear() < 0 || book.getPublishinYear() > date.getYear()) {
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
    public void save(Book book) {
        validate(book);
        bookRepository.save(book);
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
    public List<Book> getByAuthor(String author) {
        log.info("Received a list of books by author from BookRepository");
        return bookRepository.findByAuthor(author)
                .stream()
                .toList();
    }

    public BigDecimal sumPriceByAuthor (String author) {
        log.info("Calculation of the cost of all books of the author");
        return bookRepository.findByAuthor(author)
                .stream()
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
