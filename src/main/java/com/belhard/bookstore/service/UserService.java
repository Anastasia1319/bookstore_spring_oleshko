package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.UserServiceDto;

import java.util.List;

public interface UserService {
    List<UserServiceDto> getAll();
    UserServiceDto getByEmail (String email);
    UserServiceDto getById (Long id);
    UserServiceDto create (UserServiceDto dto);
    UserServiceDto update (UserServiceDto dto);
    void delete(Long id);
    UserServiceDto login(String email, String password);
    List<UserServiceDto> getAllWithNotActive();
}