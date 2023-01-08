package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderDao;
import com.belhard.bookstore.data.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {
    @Override
    public OrderDto findById(Long key) {
        return null;
    }

    @Override
    public List<OrderDto> findAll() {
        return null;
    }

    @Override
    public OrderDto create(OrderDto entity) {
        return null;
    }

    @Override
    public OrderDto update(OrderDto entity) {
        return null;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }
}
