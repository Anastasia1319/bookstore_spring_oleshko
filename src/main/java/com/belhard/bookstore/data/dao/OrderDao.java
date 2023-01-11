package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.OrderDto;

import java.util.List;

public interface OrderDao extends CrudDao<OrderDto, Long>{
    List<OrderDto> findByUserId(Long id);
}
