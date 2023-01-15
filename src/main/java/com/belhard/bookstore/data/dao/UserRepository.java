package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    int countAll ();
    List<User> findAllWithNotActive();
}
