package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>{
    Book findByIsbn (String isbn);
    List<Book> findByAuthor (String author);
    long countAll();
}
