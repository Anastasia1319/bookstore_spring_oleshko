package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Book;

import java.util.List;

public interface BookDao extends CrudDao<Book, Long>{
    Book findByIsbn (String isbn);
    List<Book> findByAuthor (String author);
    long countAll();
}
