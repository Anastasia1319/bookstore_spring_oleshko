package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.repository.BookRepository;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookServiceDto;
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
    private final ConverterService converter;

    @Override
    public List<BookServiceDto> getAll() {
        log.info("Received a list of books from BookRepositoryImpl");
        return bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Book::getId))
                .map(converter::toBookDto)
                .toList();
    }

    @Override
    public BookServiceDto getById(Long id) {
        Book book = bookRepository.findById(id);
        log.info("The BookRepositoryImpl class method was called to search");
        if (book == null) {
            log.warn("Book with id: {} not found!", id);
            throw  new NotFoundException("Book with id: " + id + " not found!");
        }
        BookServiceDto bookServiceDto = converter.toBookDto(book);
        log.info("Search result: {}", bookServiceDto);
        return bookServiceDto;
    }

    @Override
    public BookServiceDto create(BookServiceDto dto) {
        validate(dto);
        BookServiceDto existing = converter.toBookDto(bookRepository.findByIsbn(dto.getIsbn()));
        if (existing != null) {
            log.error("A book with this ISBN already exists in the database");
            throw new NotUpdateException("A book with this ISBN already exists in the database");
        }
        Book toCreate = converter.toBookEntity(dto);
        Book created = bookRepository.create(toCreate);
        BookServiceDto bookServiceDto = converter.toBookDto(created);
        log.info("Creation result: {}", bookServiceDto);
        return bookServiceDto;
    }

    private void validate(BookServiceDto dto) {
        if (dto.getIsbn().length() > ISBN_LENGTH) {
            log.error("Isbn parameter value is invalid");
            throw new NotUpdateException("ISBN number cannot be longer than 13 characters.");
        }
        LocalDate date = LocalDate.now();
        if (dto.getPublishinYear() < 0 || dto.getPublishinYear() > date.getYear()) {
            log.error("Invalid publication year value");
            throw new NotUpdateException("Incorrect year of publication of the book entered.");
        }
        if (dto.getPrice().signum() <= 0) {
            log.error("Invalid price value");
            throw new NotUpdateException("Incorrect price of the book entered.");
        }
        log.info("Parameters have been successfully validated");
    }

    @Override
    public BookServiceDto update(BookServiceDto dto) {
        validate(dto);
        BookServiceDto existing = converter.toBookDto(bookRepository.findByIsbn(dto.getIsbn()));
        if (existing != null && existing.getId() != dto.getId()) {
            log.error("A book with this ISBN already exists in the database");
            throw new NotUpdateException("A book with this ISBN already exists in the database");
        }
        Book toUpdate = converter.toBookEntity(dto);
        Book updated = bookRepository.update(toUpdate);
        BookServiceDto bookServiceDto = converter.toBookDto(updated);
        log.info("Update result: {}", bookServiceDto);
        return bookServiceDto;
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.delete(id)) {
            log.error("Book with id {} not deleted", id);
            throw new NotFoundException("Couldn't delete book with id: " + id + "!");
        }
        log.info("Book with id {} deleted", id);
    }

    @Override
    public List<BookServiceDto> getByAuthor(String author) {
        log.info("Received a list of books by author from BookRepositoryImpl");
        return bookRepository.findByAuthor(author)
                .stream()
                .map(converter::toBookDto)
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
