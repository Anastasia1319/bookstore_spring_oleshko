package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.service.dto.BookDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Converter {
    public Book toBookEntity (BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setPublishinYear(dto.getPublishinYear());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        log.info("BookDto transformed to Book");
        return book;
    }
}
