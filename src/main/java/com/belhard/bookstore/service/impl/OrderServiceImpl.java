package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.OrderRepository;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderServiceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ConverterService converter;

    @Override
    public List<OrderServiceDto> getAll() {
        log.info("Received a list of orders from OrderRepositoryImpl");
        return orderRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getId))
                .map(converter::toOrderDto)
                .toList();
    }

    @Override
    public OrderServiceDto getById(Long id) {
        log.info("The OrderRepository method was called to search by id");
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));
        return converter.toOrderDto(order);
    }

    @Override
    public List<OrderServiceDto> getByUserId(Long id) {
        log.info("Received a list of orders by userId from OrderRepositoryImpl");
        return orderRepository.findByUserId(id)
                .stream()
                .map(converter::toOrderDto)
                .toList();
    }
}
