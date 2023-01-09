package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderItemDao;
import com.belhard.bookstore.data.dao.impl.mapper.OrderItemRowMapper;
import com.belhard.bookstore.data.dto.OrderItemDto;
import com.belhard.bookstore.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class OrderItemDaoImpl implements OrderItemDao {
    private static final String SELECT_ALL = "SELECT oi.book_id, oi.quantity, oi.price, oi.order_id FROM order_items oi";
    private static final String FIND_BY_ID = "SELECT oi.book_id, oi.quantity, oi.price, oi.order_id FROM order_items oi " +
            "WHERE oi.order_id = ? AND book_id = ?";
    private static final String FIND_BY_ORDER_ID = "SELECT oi.book_id, oi.quantity, oi.price, oi.order_id FROM order_items oi " +
            "WHERE oi.order_id = ?";
    private static final String CREATE = "INSERT INTO order_items SET book_id = ?, quantity = ?, price = ?, order_id = ?";
    private static final String UPDATE = "UPDATE order_items SET quantity = ?, price = ? WHERE order_id = ? AND book_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM order_items WHERE order_id = ?";
    private static final String COUNT_TOTAL_COST = "SELECT SUM(oi.quantity*oi.price) FROM order_items oi WHERE oi.order_id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final OrderItemRowMapper rowMapper;

    @Override
    public OrderItemDto findById(Long key) {
        return null;
    }

    @Override
    public List<OrderItemDto> findAll() {
        log.info("Created a list of orderItems matching the search criteria");
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public OrderItemDto create(OrderItemDto entity) {
        log.info("Object creation method called");
        return null;
    }

    @Override
    public OrderItemDto update(OrderItemDto entity) {
        return null;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<OrderItemDto> findByOrderId(Long orderId) {
        log.info("Find list orderItems by order id");
        return jdbcTemplate.query(FIND_BY_ORDER_ID, rowMapper, orderId);
    }

    @Override
    public BigDecimal totalCost(Long orderId) {
        log.info("Trying find order total cost");
        BigDecimal totalCost =  jdbcTemplate.queryForObject(COUNT_TOTAL_COST, BigDecimal.class);
        if (totalCost != null) {
            return totalCost;
        } else {
            log.warn("Returned null from totalCost SQL query");
            throw new NotFoundException("Unable to calculate order amount");
        }
    }
}
