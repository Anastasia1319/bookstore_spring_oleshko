package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.BookServiceDto;

import java.util.List;

public interface BookService {

    List<BookServiceDto> getAll();
    BookServiceDto getById (Long id);
    BookServiceDto create (BookServiceDto dto);
    BookServiceDto update (BookServiceDto dto);
    void delete(Long id);
    List<BookServiceDto> getByAuthor(String author);
}
