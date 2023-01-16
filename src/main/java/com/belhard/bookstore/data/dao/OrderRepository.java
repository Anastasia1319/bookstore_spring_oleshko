package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("from Order o where o.user = :userId")
    List<Order> findByUserId(Long userId);
}
