package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.dto.BookDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.repository.BookRepository;
import com.belhard.bookstore.data.repository.Converter;;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class BookRepositoryImpl implements BookRepository {
    private final BookDao bookDao;
    private final Converter converter;
    @Override
    public Book findById(Long key) {
        BookDto bookDto = bookDao.findById(key);
        Book book = converter.toBookEntity(bookDto);
        log.info("Search result by id is transformed to a book object");
        return book;
    }

    @Override
    public List<Book> findAll() {
        log.info("Received a list of books from BookDaoImpl");
        return bookDao.findAll()
                .stream()
                .map(converter::toBookEntity)
                .toList();
    }

    @Override
    public Book create(Book entity) {
        BookDto toCreat = converter.toDto(entity);
        BookDto created = bookDao.create(toCreat);
        Book book = converter.toBookEntity(created);
        log.info("Creation result: {}", book);
        return book;
    }

    @Override
    public Book update(Book entity) {
        BookDto bookDto = converter.toDto(entity);
        BookDto updated = bookDao.update(bookDto);
        Book book = converter.toBookEntity(updated);
        log.info("Update result: {}", book);
        return book;
    }

    @Override
    public boolean delete(Long key) {
        log.info("Book with id {} is being deleted...", key);
        return bookDao.delete(key);
    }

    @Override
    public Book findByIsbn(String isbn) {
        BookDto bookDto = bookDao.findByIsbn(isbn);
        Book book = converter.toBookEntity(bookDto);
        log.info("Book with ISBN {} is found", isbn);
        return book;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        log.info("Received a list of books by author from BookDaoImpl");
        return bookDao.findByAuthor(author)
                .stream()
                .map(converter::toBookEntity)
                .toList();
    }

    @Override
    public long countAll() {
        return bookDao.countAll();
    }
}
