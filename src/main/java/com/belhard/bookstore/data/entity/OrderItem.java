package com.belhard.bookstore.data.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private Book book;
    private Integer quantity;
    private BigDecimal price;
}
