package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.entity.UserDto;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import com.belhard.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<com.belhard.bookstore.service.dto.UserDto> getAll() {
        log.info("Received a list of users from UserDaoImpl");
        return userDao.findAll()
                .stream()
                .sorted(Comparator.comparing(UserDto::getId))
                .map(this::toDto)
                .toList();
    }

    @Override
    public com.belhard.bookstore.service.dto.UserDto getByEmail(String email) {
        UserDto user = userDao.findByEmail(email);
        log.info("The UserDaoImpl class method was called to search");
        if (user == null) {
            log.warn("User with email: {} not found!", email);
            throw new NotFoundException("User with email: " + email + " not found!");
        }
        com.belhard.bookstore.service.dto.UserDto userDto = toDto(user);
        log.info("Search result: {}", userDto);
        return userDto;
    }

    @Override
    public com.belhard.bookstore.service.dto.UserDto getById(Long id) {
        UserDto user = userDao.findById(id);
        log.info("The UserDaoImpl class method was called to search");
        if (user == null) {
            log.warn("User with email: {} not found!", id);
            throw new NotFoundException("User with id: " + id + " not found!");
        }
        com.belhard.bookstore.service.dto.UserDto userDto = toDto(user);
        log.info("Search result: {}", userDto);
        return userDto;
    }

    @Override
    public com.belhard.bookstore.service.dto.UserDto create(com.belhard.bookstore.service.dto.UserDto dto) {
        validate(dto);
        UserDto toCreate = toEntity(dto);
        UserDto created = userDao.create(toCreate);
        com.belhard.bookstore.service.dto.UserDto userDto = toDto(created);
        log.info("Creation result: {}", userDto);
        return userDto;
    }

    private void validate (com.belhard.bookstore.service.dto.UserDto dto) {
        if (dto.getPassword().length() < 8) {
            log.error("Password shorter 8 characters");
            throw new NotUpdateException("Password cannot be shorter than 8 characters.");
        }
        log.info("Parameters have been successfully validated");
    }

    @Override
    public com.belhard.bookstore.service.dto.UserDto update(com.belhard.bookstore.service.dto.UserDto dto) {
        validate(dto);
        UserDto toUpdate = toEntity(dto);
        UserDto updated = userDao.update(toUpdate);
        com.belhard.bookstore.service.dto.UserDto userDto = toDto(updated);
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

    public com.belhard.bookstore.service.dto.UserDto login(String email, String password) {
        UserDto user = userDao.findByEmail(email);
        if (user == null || !password.equals(user.getPassword())) {
            log.error("Incorrect email or password");
            throw new NotFoundException("User with email: " + email + "and with password: " + password + " not found!");
        }
        com.belhard.bookstore.service.dto.UserDto userDto = toDto(user);
        log.info("Login completed");
        return userDto;
    }

    private com.belhard.bookstore.service.dto.UserDto toDto(UserDto user) {
        com.belhard.bookstore.service.dto.UserDto userDto = new com.belhard.bookstore.service.dto.UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        log.info("User transformed to UserDto");
        return userDto;
    }

    private UserDto toEntity (com.belhard.bookstore.service.dto.UserDto dto) {
        UserDto user = new UserDto();
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
