package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dto.OrderDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Log4j2
public class OrderRowMapper implements RowMapper<OrderDto> {
    @Override
    public OrderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(rs.getLong("id"));
        orderDto.setUserId(rs.getLong("user_id"));
        orderDto.setStatus(OrderDto.Status.valueOf(rs.getString("status_name")));
        return orderDto;
    }
}
