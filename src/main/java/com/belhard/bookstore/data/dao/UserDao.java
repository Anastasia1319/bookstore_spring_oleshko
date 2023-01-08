package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.User;

import java.util.List;

public interface UserDao extends CrudDao <User, Long>{
    User findByEmail(String email);
    int countAll ();
}
