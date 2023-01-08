package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderDao;
import com.belhard.bookstore.data.dao.impl.mapper.OrderRowMapper;
import com.belhard.bookstore.data.dto.OrderDto;
import com.belhard.bookstore.exceptions.NotUpdateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {
    private static final String SELECT_ALL = "SELECT o.id, o.user_id, s.status_name FROM orders o JOIN statuses s " +
            "ON s.id = o.status_id";
    private static final String FIND_BY_ID = "SELECT o.id, o.user_id, s.status_name FROM orders o JOIN statuses s " +
            "ON s.id = o.status_id WHERE o.id = ?";
    private static final String CREATE = "INSERT INTO orders (user_id, status_id) VALUES (?, (SELECT s.id FROM statuses s " +
            "WHERE s.status_name = ?))";
    private static final String UPDATE = "UPDATE orders SET user_id = ?, status_id = (SELECT s.id FROM statuses s " +
            "WHERE s.status_name = ?) WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;

    private final OrderRowMapper rowMapper;
    @Override
    public OrderDto findById(Long key) {
        log.info("Order with id {} found", key);
        return jdbcTemplate.queryForObject(FIND_BY_ID, rowMapper, key);
    }

    @Override
    public List<OrderDto> findAll() {
        log.info("Created a list of orders matching the search criteria");
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public OrderDto create(OrderDto entity) {
        log.info("Object creation method called");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE, new String[]{"id"});
            mapOrderToStatementData(entity, statement);
            return statement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::findById)
                .orElseThrow();
    }

    @Override
    public OrderDto update(OrderDto entity) {
        log.info("Trying to update a row with a order in the database");
        int rowsUpdated = jdbcTemplate.update(UPDATE, entity.getUserId(), entity.getStatus().toString(), entity.getId());
        if (rowsUpdated == 0) {
            log.warn("Updated rows (orders): 0");
            throw new NotUpdateException("Couldn't update order: {}" + entity);
        }
        log.info("Order is updated");
        return findById(entity.getId());
    }

    @Override
    public boolean delete(Long key) {
        log.info("Trying to delete a row with a order in the database");
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, key);
        if (rowsUpdated == 0) {
            log.warn("Updated rows on deletion (orders): 0");
            throw new NotUpdateException("Couldn't delete order with id: " + key);
        }
        log.info("Order is deleted");
        return rowsUpdated == 1;
    }

    private void mapOrderToStatementData (OrderDto orderDto, PreparedStatement statement) throws SQLException {
        statement.setLong(1, orderDto.getUserId());
        statement.setString(2, orderDto.getStatus().toString());
    }
}
