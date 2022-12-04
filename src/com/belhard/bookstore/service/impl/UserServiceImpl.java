package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<UserDto> getAll() {
        return userDao.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User with email: " + email + " not found!");
        }
        return toDto(user);
    }

    @Override
    public UserDto create(UserDto dto) {
        validate(dto);
        User toCreate = toEntity(dto);
        User created = userDao.create(toCreate);
        return toDto(created);
    }

    private void validate (UserDto dto) {
        if (dto.getPassword().length() < 8) {
            throw new NotUpdateException("Password cannot be shorter than 8 characters.");
        }
    }

    @Override
    public UserDto update(UserDto dto) {
        validate(dto);
        User toUpdate = toEntity(dto);
        User updated = userDao.update(toUpdate);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!userDao.delete(id)) {
            throw new NotFoundException("Couldn't delete user with id: " + id + "!");
        }
    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }

    private User toEntity (UserDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }
}
