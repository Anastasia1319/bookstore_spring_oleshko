package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.OrderServiceDto;
import com.belhard.bookstore.service.dto.UserServiceDto;

import java.util.List;

public interface OrderService {
    List<OrderServiceDto> getAll();
    OrderServiceDto getById (Long id);
    List<OrderServiceDto> getByUserId (UserServiceDto userDto);
}
