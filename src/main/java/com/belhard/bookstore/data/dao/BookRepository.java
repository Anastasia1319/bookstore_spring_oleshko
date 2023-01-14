package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.BookDto;

import java.util.List;

public interface BookRepository extends CrudRepository<BookDto, Long> {
    BookDto findByIsbn (String isbn);
    List<BookDto> findByAuthor (String author);
    long countAll();
}
