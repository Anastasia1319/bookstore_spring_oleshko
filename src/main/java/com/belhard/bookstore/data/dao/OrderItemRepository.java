package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId (Order order);
    BigDecimal findTotalCost(Order order);
}
