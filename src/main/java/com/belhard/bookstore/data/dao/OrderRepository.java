package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserId(User user);
}
