package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User create (User user);
    User update (User user);
    boolean delete (Long id);
    User findByEmail(String email);
    int countAll ();
    User findById(Long id);
}
