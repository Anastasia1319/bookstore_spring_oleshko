package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dao.OrderRepository;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderRepositoryImpl implements com.belhard.bookstore.data.repository.OrderRepository {
    private final OrderRepository orderRepository;

    @Override
    public Order findById(Long key) {
        log.info("Trying find order by id");
        return orderRepository.findById(key)
                .orElseThrow(() -> new NotFoundException("Order with id " + key + " not found"));
    }

    @Override
    public List<Order> findAll() {
        log.info("Received a list of orders from OrderDaoImpl");
        return orderRepository.findAll()
                .stream()
                .toList();
    }

    @Override
    public void save (Order entity) {
        log.info("Trying to save");
        orderRepository.save(entity);
    }

    @Override
    public boolean delete(Long key) {
        return orderRepository.delete(key);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        log.info("Received a list of orders by userId from OrderDaoImpl");
        return orderRepository.findByUserId(id)
                .stream()
                .toList();
    }
}
