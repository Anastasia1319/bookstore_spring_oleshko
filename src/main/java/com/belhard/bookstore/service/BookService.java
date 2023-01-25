package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.BookDto;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    List<BookDto> getAll(Pageable pageable);
    BookDto getById (Long id);
    void save (BookDto book);
    void delete(Long id);
    List<BookDto> getByAuthor(String author, Pageable pageable);
    BookDto getByIsbn(String isbn);
    BigDecimal sumPriceByAuthor (String author, Pageable pageable);
    Long totalPages (Integer pageSize);
    Long totalPagesAuthor (Integer pageSize, String author);
}
