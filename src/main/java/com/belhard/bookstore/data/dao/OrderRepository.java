package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId, Pageable pageable);
    Integer countByUserId(Long userId);
}
