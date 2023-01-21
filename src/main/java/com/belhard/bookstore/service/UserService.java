package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.UserServiceDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserServiceDto> getAll(Pageable pageable);
    UserServiceDto getByEmail (String email);
    UserServiceDto getById (Long id);
    void save (UserServiceDto user);
    void delete(Long id);
    UserServiceDto login(String email, String password);
    List<UserServiceDto> getAllWithNotActive(Pageable pageable);
    Long totalPages (Integer pageSize);
    Long totalPagesActive (Integer pageSize);
}