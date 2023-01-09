package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.dao.OrderDao;
import com.belhard.bookstore.data.dao.OrderItemDao;
import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.dto.OrderDto;
import com.belhard.bookstore.data.dto.OrderItemDto;
import com.belhard.bookstore.data.dto.UserDto;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderItem;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.Converter;
import com.belhard.bookstore.data.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final UserDao userDao;
    private final BookDao bookDao;
    private final Converter converter;
    @Override
    public Order findById(Long key) {
        log.info("Trying find order by id");
        OrderDto orderDto = orderDao.findById(key);
        log.info("OrderDto is fond");
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setStatus(Order.Status.valueOf(orderDto.getStatus().toString()));
        order.setTotalCost(orderDto.getTotalCost());
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
        log.info("Created order");
        return order;
    }


    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }
}
