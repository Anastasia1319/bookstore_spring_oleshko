package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.dto.BookDto;
import com.belhard.bookstore.data.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>{
    BookDto findByIsbn (String isbn);
    List<BookDto> findByAuthor (String author);
    long countAll();
}
