package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByIsbn (String isbn);
    List<Book> findByAuthor (String author);
    long countAll();
}
