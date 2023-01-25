package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDto> getAll(Pageable pageable);
    UserDto getByEmail (String email);
    UserDto getById (Long id);
    void save (UserDto user);
    void delete(Long id);
    UserDto login(String email, String password);
    List<UserDto> getAllWithNotActive(Pageable pageable);
    Long totalPages (Integer pageSize);
    Long totalPagesActive (Integer pageSize);
}