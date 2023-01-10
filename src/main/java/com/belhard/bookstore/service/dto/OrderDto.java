package com.belhard.bookstore.service.dto;

import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.entity.OrderItem;
import com.belhard.bookstore.data.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private User user;
    private BigDecimal totalCost;
    private Status status;
    private Book book;
    private List<OrderItem> items;
    private Integer quantity;
    private BigDecimal price;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED
    }
}
