package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderItemDao;
import com.belhard.bookstore.data.dao.impl.mapper.OrderItemRowMapper;
import com.belhard.bookstore.data.dto.OrderItemDto;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class OrderItemDaoImpl implements OrderItemDao {
    private static final String SELECT_ALL = "SELECT oi.id, oi.book_id, oi.quantity, oi.price, oi.order_id FROM order_items oi";
    private static final String FIND_BY_ID = "SELECT oi.id, oi.book_id, oi.quantity, oi.price, oi.order_id FROM order_items oi " +
            "WHERE oi.id = ?";
    private static final String FIND_BY_ORDER_ID = "SELECT oi.id, oi.book_id, oi.quantity, oi.price, oi.order_id " +
            "FROM order_items oi WHERE oi.order_id = ?";
    private static final String CREATE = "INSERT INTO order_items SET book_id = ?, quantity = ?, price = ?, order_id = ?";
    private static final String UPDATE = "UPDATE order_items SET book_id = ?, quantity = ?, price = ?, order_id = ? WHERE id =?";
    private static final String DELETE_BY_ID = "DELETE FROM order_items WHERE id = ?";
    private static final String COUNT_TOTAL_COST = "SELECT SUM(oi.quantity*oi.price) FROM order_items oi WHERE oi.order_id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final OrderItemRowMapper rowMapper;

    @Override
    public OrderItemDto findById(Long key) {
        log.info("OrderItem with id {} found", key);
        return jdbcTemplate.queryForObject(FIND_BY_ID, rowMapper, key);
    }

    @Override
    public List<OrderItemDto> findAll() {
        log.info("Created a list of orderItems matching the search criteria");
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public OrderItemDto create(OrderItemDto entity) {
        log.info("Object creation method called");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE, new String[]{"id"});
            mapOrderItemToStatementData(entity, statement);
            return statement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::findById)
                .orElseThrow();
    }

    @Override
    public OrderItemDto update(OrderItemDto entity) {
        log.info("Trying to update a row with a orderItem in the database");
        int rowsUpdated = jdbcTemplate.update(UPDATE, entity.getBookId(), entity.getQuantity(), entity.getPrice(),
                entity.getOrderId(), entity.getId());
        if (rowsUpdated == 0) {
            log.warn("Updated rows (orderItem): 0");
            throw  new NotUpdateException("Couldn't update orderItem: {}" + entity);
        }
        log.info("OrderItem is updated");
        return findById(entity.getId());
    }

    @Override
    public boolean delete(Long key) {
        log.info("Trying to delete a row with a orderItem in the database");
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, key);
        if (rowsUpdated == 0) {
            log.warn("Updated rows (orderItem): 0");
            throw  new NotUpdateException("Couldn't delete orderItem with id: {}" + key);
        }
        log.info("OrderItem is deleted");
        return rowsUpdated == 1;
    }

    @Override
    public List<OrderItemDto> findByOrderId(Long orderId) {
        log.info("Find list orderItems by order id");
        return jdbcTemplate.query(FIND_BY_ORDER_ID, rowMapper, orderId);
    }

    @Override
    public BigDecimal findTotalCost(Long orderId) {
        log.info("Trying find order total cost");
        BigDecimal totalCost =  jdbcTemplate.queryForObject(COUNT_TOTAL_COST, BigDecimal.class, orderId);
        if (totalCost != null) {
            return totalCost;
        } else {
            log.warn("Returned null from totalCost SQL query");
            throw new NotFoundException("Unable to calculate order amount");
        }
    }

    private void mapOrderItemToStatementData (OrderItemDto orderItemDto, PreparedStatement statement) throws SQLException {
        statement.setLong(1, orderItemDto.getBookId());
        statement.setInt(2, orderItemDto.getQuantity());
        statement.setBigDecimal(3, orderItemDto.getPrice());
        statement.setLong(4, orderItemDto.getOrderId());
    }
}
