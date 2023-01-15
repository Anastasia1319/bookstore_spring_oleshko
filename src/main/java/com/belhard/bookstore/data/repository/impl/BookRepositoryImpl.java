package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dao.BookRepository;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.repository.Converter;;
import com.belhard.bookstore.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class BookRepositoryImpl implements com.belhard.bookstore.data.repository.BookRepository {
    private final BookRepository bookRepository;

    @Override
    public Book findById(Long key) {
        log.info("Search result by id is transformed to a book object");
        return bookRepository.findById(key)
                .orElseThrow(() -> new NotFoundException("Book with id " + key + " not found"));
    }

    @Override
    public List<Book> findAll() {
        log.info("Received a list of books from BookDaoImpl");
        return bookRepository.findAll()
                .stream()
                .toList();
    }

    @Override
    public void save (Book entity) {
        bookRepository.save(entity);
        log.info("Save result: {}", entity);
    }

    @Override
    public boolean delete(Long key) {
        log.info("Book with id {} is being deleted...", key);
        return bookRepository.delete(key);
    }

    @Override
    public Book findByIsbn(String isbn) {
        log.info("Book with ISBN {} is finding", isbn);
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("User with ISBN " + isbn+ " not found"));
    }

    @Override
    public List<Book> findByAuthor(String author) {
        log.info("Received a list of books by author from BookDaoImpl");
        return bookRepository.findByAuthor(author)
                .stream()
                .toList();
    }

    @Override
    public long countAll() {
        return bookRepository.countAll();
    }
}
