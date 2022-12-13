package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();
    Book findById (Long id);
    Book create (Book book);
    Book update (Book book);
    boolean delete(Long id);
    Book findByIsbn (String isbn);
    List<Book> findByAuthor (String author);
    long countAll();
}
