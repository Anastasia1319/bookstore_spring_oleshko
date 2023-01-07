package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAll();
    BookDto getById (Long id);
    BookDto create (BookDto dto);
    BookDto update (BookDto dto);
    void delete(Long id);
    List<BookDto> getByAuthor(String author);
}
