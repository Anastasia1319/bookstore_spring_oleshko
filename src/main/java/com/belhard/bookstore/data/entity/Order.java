package com.belhard.bookstore.data.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;
    private User user;
    private BigDecimal totalCost;
    private List<OrderItem> items;
    private Status status;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED
    }
}
