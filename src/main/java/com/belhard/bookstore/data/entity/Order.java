package com.belhard.bookstore.data.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private Long id;
    private User user;
    private BigDecimal totalCost;
    private Status status;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED
    }
}
