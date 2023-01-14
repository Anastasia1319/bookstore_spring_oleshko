package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.OrderDto;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDto, Long> {
    List<OrderDto> findByUserId(Long id);
}
