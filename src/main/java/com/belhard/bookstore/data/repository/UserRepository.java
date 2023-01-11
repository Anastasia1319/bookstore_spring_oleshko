package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.entity.User;

public interface UserRepository extends CrudRepository <User, Long> {
    User findByEmail(String email);
    int countAll ();
}
