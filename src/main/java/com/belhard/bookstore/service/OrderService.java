package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.OrderServiceDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<OrderServiceDto> getAll(Pageable pageable);
    OrderServiceDto getById (Long id);
    List<OrderServiceDto> getByUserId (Long id, Pageable pageable);
    Long totalPages (Integer pageSize);
}
