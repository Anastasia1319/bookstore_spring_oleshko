package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    public static final int ISBN_LENGTH = 13;
    private final BookDao bookDao;

    @Override
    public List<BookDto> getAll() {
        log.info("Received a list of books from BookDaoImpl");
        return bookDao.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookDao.findById(id);
        log.info("The BookDaoImpl class method was called to search");
        if (book == null) {
            log.warn("Book with id: {} not found!", id);
            throw  new NotFoundException("Book with id: " + id + " not found!");
        }
        BookDto bookDto = toDto(book);
        log.info("Search result: {}", bookDto);
        return bookDto;
    }

    @Override
    public BookDto create(BookDto dto) {
        validate(dto);
        Book toCreate = toEntity(dto);
        Book created = bookDao.create(toCreate);
        BookDto bookDto = toDto(created);
        log.info("Creation result: {}", bookDto);
        return bookDto;
    }

    private void validate(BookDto dto) {
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
    public BookDto update(BookDto dto) {
        validate(dto);
        Book toUpdate = toEntity(dto);
        Book updated = bookDao.update(toUpdate);
        BookDto bookDto = toDto(updated);
        log.info("Update result: {}", bookDto);
        return bookDto;
    }

    @Override
    public void delete(Long id) {
        if (!bookDao.delete(id)) {
            log.error("Book with id {} not deleted", id);
            throw new NotFoundException("Couldn't delete book with id: " + id + "!");
        }
        log.info("Book with id {} deleted", id);
    }

    @Override
    public List<BookDto> getByAuthor(String author) {
        log.info("Received a list of books by author from BookDaoImpl");
        return bookDao.findByAuthor(author)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublishinYear(book.getPublishinYear());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPrice(book.getPrice());
        log.info("Book transformed to BookDto");
        return bookDto;
    }

    private Book toEntity (BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setPublishinYear(dto.getPublishinYear());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        log.info("BookDto transformed to Book");
        return book;
    }

    public BigDecimal sumPriceByAuthor (String author) {
        log.info("Calculation of the cost of all books of the author");
        return bookDao.findByAuthor(author)
                .stream()
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
