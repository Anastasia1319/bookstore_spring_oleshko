package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.UserRepository;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
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
    public List<UserDto> getAll() {
        log.info("Received a list of users from UserDaoImpl");
        return userRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getId))
                .map(converter::toUserDto)
                .toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email);
        log.info("The UserRepositoryImpl class method was called to search");
        if (user == null) {
            log.warn("User with email: {} not found!", email);
            throw new NotFoundException("User with email: " + email + " not found!");
        }
        UserDto userDto = converter.toUserDto(user);
        log.info("Search result: {}", userDto);
        return userDto;
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id);
        log.info("The UserRepositoryImpl class method was called to search");
        if (user == null) {
            log.warn("User with email: {} not found!", id);
            throw new NotFoundException("User with id: " + id + " not found!");
        }
        UserDto userDto = converter.toUserDto(user);
        log.info("Search result: {}", userDto);
        return userDto;
    }

    @Override
    public UserDto create(UserDto dto) {
        validate(dto);
        User toCreate = converter.toUserEntity(dto);
        User created = userRepository.create(toCreate);
        UserDto userDto = converter.toUserDto(created);
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
        User toUpdate = converter.toUserEntity(dto);
        User updated = userRepository.update(toUpdate);
        UserDto userDto = converter.toUserDto(updated);
        log.info("Update result: {}", userDto);
        return userDto;
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.delete(id)) {
            log.error("User with id {} not deleted", id);
            throw new NotFoundException("Couldn't delete user with id: " + id + "!");
        }
        log.info("User with id {} deleted", id);
    }

    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !password.equals(user.getPassword())) {
            log.error("Incorrect email or password");
            throw new NotFoundException("User with email: " + email + "and with password: " + password + " not found!");
        }
        UserDto userDto = converter.toUserDto(user);
        log.info("Login completed");
        return userDto;
    }
}
