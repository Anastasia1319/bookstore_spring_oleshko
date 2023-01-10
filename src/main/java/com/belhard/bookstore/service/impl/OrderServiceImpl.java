package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.repository.OrderRepository;
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
        Order order = orderRepository.findById(id);
        log.info("The OrderRepositoryImpl class method was called to search");
        if (order == null) {
            log.warn("Order with id: {} not found!", id);
            throw new NotFoundException("Order with id: " + id + " not found!");
        }
        OrderServiceDto orderServiceDto = converter.toOrderDto(order);
        log.info("Search result: {}", orderServiceDto);
        return orderServiceDto;
    }
}
