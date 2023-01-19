package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.BookServiceDto;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    List<BookServiceDto> getAll(Pageable pageable);
    BookServiceDto getById (Long id);
    void save (BookServiceDto book);
    void delete(Long id);
    List<BookServiceDto> getByAuthor(String author, Pageable pageable);
    BookServiceDto getByIsbn(String isbn);
    BigDecimal sumPriceByAuthor (String author, Pageable pageable);
    Integer totalPages (Integer pageSize);
    Integer totalPagesAuthor (Integer pageSize, String author);
}
