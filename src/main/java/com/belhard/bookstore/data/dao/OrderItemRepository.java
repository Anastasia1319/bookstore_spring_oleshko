package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("from OrderItem where orderId = :orderId")
    List<OrderItem> findByOrderId (Long orderId);

    @Query(value = "SELECT SUM(oi.quantity * oi.price) from order_items oi where oi.order_id = ?", nativeQuery = true)
    BigDecimal findTotalCost(Long orderId);
}
