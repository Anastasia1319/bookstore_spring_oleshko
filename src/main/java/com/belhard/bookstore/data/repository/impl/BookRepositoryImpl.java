package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dto.BookDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final BookDto bookDto;
    @Override
    public Book findById(Long key) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book create(Book entity) {
        return null;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public Book findByIsbn(String isbn) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public long countAll() {
        return 0;
    }
}
