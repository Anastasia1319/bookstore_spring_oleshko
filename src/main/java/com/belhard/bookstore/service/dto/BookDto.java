package com.belhard.bookstore.service.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BookDto {
    private Long id;
    private String author;
    private String title;
    private Integer publishinYear;
    private String isbn;
    private BigDecimal price;
}
