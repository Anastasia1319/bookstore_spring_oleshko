package com.belhard.bookstore.data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Status status;

    public enum Status {
        PENDING, PAID, DELIVERED, CANCELED
    }
}
