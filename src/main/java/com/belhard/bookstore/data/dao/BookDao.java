package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dao.dto.BookDto;
import com.belhard.bookstore.data.entity.Book;

import java.util.List;

public interface BookDao extends CrudDao<BookDto, Long>{
    BookDto findByIsbn (String isbn);
    List<BookDto> findByAuthor (String author);
    long countAll();
}
