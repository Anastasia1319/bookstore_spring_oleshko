package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.UserDto;

import java.util.List;

public interface UserDao extends CrudDao <UserDto, Long>{
    UserDto findByEmail(String email);
    int countAll ();
    List<UserDto> findAllWithNotActive();
}
