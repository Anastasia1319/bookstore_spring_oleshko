package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.OrderItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemDao extends CrudDao<OrderItemDto, Long>{
    List<OrderItemDto> findByOrderId (Long orderId);
    BigDecimal findTotalCost(Long orderId);
}
