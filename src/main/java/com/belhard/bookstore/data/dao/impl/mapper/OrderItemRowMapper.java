package com.belhard.bookstore.data.dao.impl.mapper;

import com.belhard.bookstore.data.dto.OrderItemDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Log4j2
public class OrderItemRowMapper implements RowMapper<OrderItemDto> {
    @Override
    public OrderItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(rs.getLong("id"));
        orderItemDto.setBookId(rs.getLong("book_id"));
        orderItemDto.setQuantity(rs.getInt("quantity"));
        orderItemDto.setPrice(rs.getBigDecimal("price"));
        orderItemDto.setOrderId(rs.getLong("order_id"));
        return orderItemDto;
    }
}
