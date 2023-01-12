package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.UserRepository;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserServiceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConverterService converter;

    @Override
    public List<UserServiceDto> getAll() {
        log.info("Received a list of users from UserDaoImpl");
        return userRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getId))
                .map(converter::toUserDto)
                .toList();
    }

    @Override
    public UserServiceDto getByEmail(String email) {
        User user = userRepository.findByEmail(email);
        log.info("The UserRepositoryImpl class method was called to search");
        if (user == null) {
            log.warn("User with email: {} not found!", email);
            throw new NotFoundException("User with email: " + email + " not found!");
        }
        UserServiceDto userServiceDto = converter.toUserDto(user);
        log.info("Search result: {}", userServiceDto);
        return userServiceDto;
    }

    @Override
    public UserServiceDto getById(Long id) {
        User user = userRepository.findById(id);
        log.info("The UserRepositoryImpl class method was called to search");
        if (user == null) {
            log.warn("User with email: {} not found!", id);
            throw new NotFoundException("User with id: " + id + " not found!");
        }
        UserServiceDto userServiceDto = converter.toUserDto(user);
        log.info("Search result: {}", userServiceDto);
        return userServiceDto;
    }

    @Override
    public UserServiceDto create(UserServiceDto dto) {
        validate(dto);
        UserServiceDto existing = converter.toUserDto(userRepository.findByEmail(dto.getEmail()));
        if (existing != null) {
            log.error("A user with this email already exists in the database");
            throw new NotUpdateException("Can't create: a user with this email already exists in the database");
        }
        User toCreate = converter.toUserEntity(dto);
        User created = userRepository.create(toCreate);
        UserServiceDto userServiceDto = converter.toUserDto(created);
        log.info("Creation result: {}", userServiceDto);
        return userServiceDto;
    }

    private void validate (UserServiceDto dto) {
        if (dto.getPassword().length() < 8) {
            log.error("Password shorter 8 characters");
            throw new NotUpdateException("Password cannot be shorter than 8 characters.");
        }
        log.info("Parameters have been successfully validated");
    }

    @Override
    public UserServiceDto update(UserServiceDto dto) {
        validate(dto);
        UserServiceDto existing = converter.toUserDto(userRepository.findByEmail(dto.getEmail()));
        if (existing != null && existing.getId() != dto.getId()) {
            log.error("A user with this email already exists in the database");
            throw new NotUpdateException("Can't update: a user with this email already exists in the database");
        }
        User toUpdate = converter.toUserEntity(dto);
        User updated = userRepository.update(toUpdate);
        UserServiceDto userServiceDto = converter.toUserDto(updated);
        log.info("Update result: {}", userServiceDto);
        return userServiceDto;
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.delete(id)) {
            log.error("User with id {} not deleted", id);
            throw new NotFoundException("Couldn't delete user with id: " + id + "!");
        }
        log.info("User with id {} deleted", id);
    }

    public UserServiceDto login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !password.equals(user.getPassword())) {
            log.error("Incorrect email or password");
            throw new NotFoundException("User with email: " + email + "and with password: " + password + " not found!");
        }
        UserServiceDto userServiceDto = converter.toUserDto(user);
        log.info("Login completed");
        return userServiceDto;
    }
}
