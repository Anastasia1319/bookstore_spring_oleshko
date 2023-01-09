package com.belhard.bookstore.data.dao.impl.mapper;

import com.belhard.bookstore.data.dto.OrderItemDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItemDto> {
    @Override
    public OrderItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setBookId(rs.getLong("book_id"));
        orderItemDto.setQuantity(rs.getInt("quantity"));
        orderItemDto.setPrice(rs.getBigDecimal("price"));
        orderItemDto.setOrderId(rs.getLong("order_id"));
        return orderItemDto;
    }
}
