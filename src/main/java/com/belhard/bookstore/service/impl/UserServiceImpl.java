package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.UserRepository;
import com.belhard.bookstore.data.entity.User;

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
        return userRepository.findAllActiveUsers()
                .stream()
                .sorted(Comparator.comparing(User::getId))
                .map(converter::toUserDto)
                .toList();
    }

    @Override
    public UserServiceDto getByEmail(String email) {
        log.info("The UserRepository method was called to search by email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
        return converter.toUserDto(user);
    }

    @Override
    public UserServiceDto getById(Long id) {
        log.info("The UserRepository method was called to search by id");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        return converter.toUserDto(user);
    }

    private void validate (UserServiceDto user) {
        if (user.getPassword().length() < 8) {
            log.error("Password shorter 8 characters");
            throw new NotUpdateException("Password cannot be shorter than 8 characters.");
        }
        log.info("Parameters have been successfully validated");
    }

    @Override
    public void save(UserServiceDto user) {
        validate(user);
        userRepository.save(converter.toUserEntity(user));
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        user.setActive(false);
        userRepository.save(user);
        log.info("User with id {} deleted", id);
    }

    public UserServiceDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
        log.info("Login completed");
        return converter.toUserDto(user);
    }

    @Override
    public List<UserServiceDto> getAllWithNotActive() {
        log.info("Received a list of all users (active and not-active) from UserDaoImpl");
        return userRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getId))
                .map(converter::toUserDto)
                .toList();
    }
}
