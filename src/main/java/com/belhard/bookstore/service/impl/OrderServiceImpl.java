package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.OrderRepository;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ConverterService converter;

    @Override
    public List<OrderDto> getAll(Pageable pageable) {
        log.info("Received a list of orders from OrderRepositoryImpl");
        return orderRepository.findAll(pageable)
                .stream()
                .map(converter::toOrderDto)
                .toList();
    }

    @Override
    public OrderDto getById(Long id) {
        log.info("The OrderRepository method was called to search by id");
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));
        return converter.toOrderDto(order);
    }

    @Override
    public List<OrderDto> getByUserId(Long id, Pageable pageable) {
        log.info("Received a list of orders by userId from OrderRepositoryImpl");
        return orderRepository.findByUserId(id, pageable)
                .stream()
                .map(converter::toOrderDto)
                .toList();
    }

    public Long totalPages (Integer pageSize) {
        log.info("The method for calculating the number of pages is called");
        return orderRepository.count() / pageSize;
    }
}
