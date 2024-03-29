package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u where u.isActive = true and u.id = ?1")
    Optional<User> findActiveById(Long id);

    @Query("from User u where u.isActive = true and u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("from User u where u.isActive = true")
    List<User> findAllActiveUsers(Pageable pageable);

    Integer countByIsActiveIsTrue();
}


