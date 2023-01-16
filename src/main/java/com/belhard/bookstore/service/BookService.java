package com.belhard.bookstore.service;

import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.service.dto.BookServiceDto;

import java.util.List;

public interface BookService {

    List<BookServiceDto> getAll();
    BookServiceDto getById (Long id);
    void save (Book book);
    void delete(Long id);
    List<BookServiceDto> getByAuthor(String author);
}
