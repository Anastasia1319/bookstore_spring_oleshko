package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.UserRepository;
import com.belhard.bookstore.data.entity.User;

import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConverterService converter;

    @Override
    public List<UserDto> getAll(Pageable pageable) {
        log.info("Received a list of users from UserDaoImpl");
        return userRepository.findAllActiveUsers(pageable)
                .stream()
                .map(converter::toUserDto)
                .toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        log.info("The UserRepository method was called to search by email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
        return converter.toUserDto(user);
    }

    @Override
    public UserDto getById(Long id) {
        log.info("The UserRepository method was called to search by id");
        User user = userRepository.findActiveById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        return converter.toUserDto(user);
    }

    private void validate (UserDto user) {
        if (user.getPassword().length() < 8) {
            log.error("Password shorter 8 characters");
            throw new NotUpdateException("Password cannot be shorter than 8 characters.");
        }
        log.info("Parameters have been successfully validated");
    }

    @Override
    public UserDto save(UserDto user) {
        validate(user);
        user.setActive(true);
        userRepository.save(converter.toUserEntity(user));
        User saved = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new NotUpdateException("User: " + user + " not update"));
        log.info("User: " + user + " was save");
        return converter.toUserDto(saved);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        user.setActive(false);
        userRepository.save(user);
        log.info("User with id {} deleted", id);
    }

    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
        log.info("Login completed");
        return converter.toUserDto(user);
    }

    @Override
    public List<UserDto> getAllWithNotActive(Pageable pageable) {
        log.info("Received a list of all users (active and not-active) from UserDaoImpl");
        return userRepository.findAll(pageable)
                .stream()
                .map(converter::toUserDto)
                .toList();
    }

    @Override
    public Long getTotalPages(Integer pageSize) {
        log.info("The method for calculating the number of pages is called");
        return userRepository.count() / pageSize;
    }

    @Override
    public Long getTotalPagesActive(Integer pageSize) {
        log.info("The method for calculating the number of pages with active users is called");
        return (long) (userRepository.countByIsActiveIsTrue() / pageSize);
    }
}
