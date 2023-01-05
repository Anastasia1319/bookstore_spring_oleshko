package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<UserDto> getAll() {
        log.info("Received a list of users from UserDaoImpl");
        return userDao.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getId))
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userDao.findByEmail(email);
        log.info("The UserDaoImpl class method was called to search");
        if (user == null) {
            log.warn("User with email: {} not found!", email);
            throw new NotFoundException("User with email: " + email + " not found!");
        }
        UserDto userDto = toDto(user);
        log.info("Search result: {}", userDto);
        return userDto;
    }

    @Override
    public UserDto getById(Long id) {
        User user = userDao.findById(id);
        log.info("The UserDaoImpl class method was called to search");
        if (user == null) {
            log.warn("User with email: {} not found!", id);
            throw new NotFoundException("User with id: " + id + " not found!");
        }
        UserDto userDto = toDto(user);
        log.info("Search result: {}", userDto);
        return userDto;
    }

    @Override
    public UserDto create(UserDto dto) {
        validate(dto);
        User toCreate = toEntity(dto);
        User created = userDao.create(toCreate);
        UserDto userDto = toDto(created);
        log.info("Creation result: {}", userDto);
        return userDto;
    }

    private void validate (UserDto dto) {
        if (dto.getPassword().length() < 8) {
            log.error("Password shorter 8 characters");
            throw new NotUpdateException("Password cannot be shorter than 8 characters.");
        }
        log.info("Parameters have been successfully validated");
    }

    @Override
    public UserDto update(UserDto dto) {
        validate(dto);
        User toUpdate = toEntity(dto);
        User updated = userDao.update(toUpdate);
        UserDto userDto = toDto(updated);
        log.info("Update result: {}", userDto);
        return userDto;
    }

    @Override
    public void delete(Long id) {
        if (!userDao.delete(id)) {
            log.error("User with id {} not deleted", id);
            throw new NotFoundException("Couldn't delete user with id: " + id + "!");
        }
        log.info("User with id {} deleted", id);
    }

    public UserDto login(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user == null || !password.equals(user.getPassword())) {
            log.error("Incorrect email or password");
            throw new NotFoundException("User with email: " + email + "and with password: " + password + " not found!");
        }
        UserDto userDto = toDto(user);
        log.info("Login completed");
        return userDto;
    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        log.info("User transformed to UserDto");
        return userDto;
    }

    private User toEntity (UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        log.info("UserDto transformed to User");
        return user;
    }
}
