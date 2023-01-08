package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderItemDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class OrderItemDaoImpl implements OrderItemDao {

    @Override
    public OrderItemDao findById(Long key) {
        return null;
    }

    @Override
    public List<OrderItemDao> findAll() {
        return null;
    }

    @Override
    public OrderItemDao create(OrderItemDao entity) {
        return null;
    }

    @Override
    public OrderItemDao update(OrderItemDao entity) {
        return null;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<OrderItemDao> findByOrderId(Long orderId) {
        return null;
    }
}
