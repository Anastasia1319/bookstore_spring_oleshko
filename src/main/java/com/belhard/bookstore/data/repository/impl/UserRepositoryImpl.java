package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dao.UserRepository;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class UserRepositoryImpl implements com.belhard.bookstore.data.repository.UserRepository {
    private final UserRepository userRepository;
    @Override
    public User findById(Long key) {
        log.info("Search result by id");
        return userRepository.findById(key)
                .orElseThrow(() -> new NotFoundException("User with id " + key + " not found"));
    }

    @Override
    public List<User> findAll() {
        log.info("Received a list of users from UserDaoImpl");
        return userRepository.findAll()
                .stream()
                .toList();
    }

    @Override
    public void save (User entity) {
        log.info("Update result: {}", entity);
        userRepository.save(entity);
    }

    @Override
    public boolean delete(Long key) {
        log.info("User with id {} is being deleted...", key);
        return userRepository.delete(key);
    }

    @Override
    public User findByEmail(String email) {
        log.info("Search result by email");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
    }

    @Override
    public int countAll() {
        return userRepository.countAll();
    }

    @Override
    public List<User> findAllWithNotActive() {
        log.info("Received a list of all users (active and not-active) from UserDaoImpl");
        return userRepository.findAllWithNotActive()
                .stream()
                .toList();
    }
}
