package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dao.BookRepository;
import com.belhard.bookstore.data.dao.OrderItemRepository;
import com.belhard.bookstore.data.dao.OrderRepository;
import com.belhard.bookstore.data.dao.UserRepository;
import com.belhard.bookstore.data.dto.OrderDto;
import com.belhard.bookstore.data.dto.OrderItemDto;
import com.belhard.bookstore.data.dto.UserDto;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderItem;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderRepositoryImpl implements com.belhard.bookstore.data.repository.OrderRepository {
    private final OrderRepository orderDao;
    private final OrderItemRepository orderItemDao;
    private final UserRepository userDao;
    private final BookRepository bookDao;
    private final Converter converter;
    @Override
    public Order findById(Long key) {
        log.info("Trying find order by id");
        OrderDto orderDto = orderDao.findById(key);
        log.info("OrderDto is fond");
        Order order = getOrder(orderDto);
        return order;
    }

    @Override
    public List<Order> findAll() {
        log.info("Received a list of orders from OrderDaoImpl");
        return orderDao.findAll()
                .stream()
                .map(this::getOrder)
                .toList();
    }

    @Override
    public Order create(Order entity) {
        OrderDto toCreat = getOrderDto(entity);
        OrderDto created = orderDao.create(toCreat);
        Order order = getOrder(created);
        log.info("Creation result: {}", order);
        return order;
    }

    @Override
    public Order update(Order entity) {
        OrderDto toUpdate = getOrderDto(entity);
        OrderDto updated = orderDao.update(toUpdate);
        Order order = getOrder(updated);
        log.info("Update result: {}", order);
        return order;
    }

    @Override
    public boolean delete(Long key) {
        return orderDao.delete(key);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        log.info("Received a list of orders by userId from OrderDaoImpl");
        return orderDao.findByUserId(id)
                .stream()
                .map(this::getOrder)
                .toList();
    }

    private Order getOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setStatus(Order.Status.valueOf(orderDto.getStatus().toString()));
        order.setTotalCost(orderItemDao.findTotalCost(orderDto.getId()));
        Long userId = orderDto.getUserId();
        UserDto userDto = userDao.findById(userId);
        log.info("UserDto is fond");
        User user = converter.toUserEntity(userDto);
        order.setUser(user);
        List <OrderItemDto> orderItemsDto = orderItemDao.findByOrderId(order.getId());
        log.info("Find OrderItemDto list");
        List<OrderItem> items = new ArrayList<>();
        for(OrderItemDto item : orderItemsDto) {
            items.add(converter.toOrderItemEntity(item, bookDao));
        }
        order.setItems(items);
        log.info("Created order");
        return order;
    }

    private OrderDto getOrderDto (Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setStatus(OrderDto.Status.valueOf(order.getStatus().toString()));
        User user = order.getUser();
        Long userId = user.getId();
        orderDto.setUserId(userId);
        return orderDto;
    }
}
