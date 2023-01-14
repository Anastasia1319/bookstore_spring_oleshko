package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dao.UserRepository;
import com.belhard.bookstore.data.dto.UserDto;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class UserRepositoryImpl implements com.belhard.bookstore.data.repository.UserRepository {
    private final UserRepository userDao;
    private final Converter converter;
    @Override
    public User findById(Long key) {
        UserDto userDto = userDao.findById(key);
        User user = converter.toUserEntity(userDto);
        log.info("Search result by id is transformed to a user object");
        return user;
    }

    @Override
    public List<User> findAll() {
        log.info("Received a list of users from UserDaoImpl");
        return userDao.findAll()
                .stream()
                .map(converter::toUserEntity)
                .toList();
    }

    @Override
    public User create(User entity) {
        UserDto toCreat = converter.toUserDto(entity);
        UserDto created = userDao.create(toCreat);
        User user = converter.toUserEntity(created);
        log.info("Creation result: {}", user);
        return user;
    }

    @Override
    public User update(User entity) {
        UserDto userDto = converter.toUserDto(entity);
        UserDto updated = userDao.update(userDto);
        User user = converter.toUserEntity(updated);
        log.info("Update result: {}", user);
        return user;
    }

    @Override
    public boolean delete(Long key) {
        log.info("User with id {} is being deleted...", key);
        return userDao.delete(key);
    }

    @Override
    public User findByEmail(String email) {
        UserDto userDto = userDao.findByEmail(email);
        User user = converter.toUserEntity(userDto);
        return user;
    }

    @Override
    public int countAll() {
        return userDao.countAll();
    }

    @Override
    public List<User> findAllWithNotActive() {
        log.info("Received a list of all users (active and not-active) from UserDaoImpl");
        return userDao.findAllWithNotActive()
                .stream()
                .map(converter::toUserEntity)
                .toList();
    }
}
