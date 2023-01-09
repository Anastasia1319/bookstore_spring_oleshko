package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.OrderItemDto;

import java.util.List;

public interface OrderItemDao extends CrudDao<OrderItemDto, Long>{
    List<OrderItemDto> findByOrderId (Long orderId);
}
