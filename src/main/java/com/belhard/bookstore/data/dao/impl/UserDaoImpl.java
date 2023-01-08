package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.dao.impl.mapper.UserRowMapper;
import com.belhard.bookstore.data.dto.UserDto;
import com.belhard.bookstore.exceptions.NotUpdateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private static final String SELECT_ALL = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.password, r.name_role " +
            "FROM users u JOIN role r ON u.role_id = r.role_id;";
    private static final String FIND_BY_EMAIL = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.password, " +
            "r.name_role FROM users u JOIN role r ON u.role_id = r.role_id WHERE u.email = ?";
    private static final String FIND_BY_ID = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.password, " +
            "r.name_role FROM users u JOIN role r ON u.role_id = r.role_id WHERE u.user_id = ?";
    private static final String CREATE = "INSERT INTO users (first_name, last_name, email, password, role_id) " +
            "VALUES (?, ?, ?, ?, (SELECT r.role_id FROM role r WHERE r.name_role = ?))";
    private static final String UPDATE = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, role_id = (SELECT r.role_id FROM role r WHERE r.name_role = ?)" +
            " WHERE user_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE user_id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) FROM users";
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    @Override
    public List<UserDto> findAll() {
        log.info("Created a list of users matching the search criteria");
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.info("Object creation method called");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE, new String[]{"user_id"});
            mapUserToStatementData(userDto, statement);
            return statement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::findById)
                .orElseThrow();
    }
    public UserDto findById(Long id) {
        log.info("User with id {} found", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, rowMapper, id);
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.info("Trying to update a row with a user in the database");
        int rowsUpdated = jdbcTemplate.update(UPDATE, userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
                userDto.getPassword(), userDto.getRole().name(), userDto.getId());
        if (rowsUpdated == 0) {
            log.warn("Updated rows (users): 0");
            throw new NotUpdateException("Couldn't update user: {}" + userDto);
        }
        return findById(userDto.getId());
    }

    @Override
    public boolean delete(Long id) {
        log.info("Trying to delete a row with a user in the database");
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, id);
        if (rowsUpdated == 0) {
            log.warn("Updated rows on deletion (users): 0");
            throw new NotUpdateException("Couldn't delete user with id: " + id);
        }
        log.info("User is deleted");
        return rowsUpdated == 1;
    }

    @Override
    public UserDto findByEmail(String email) {
        log.info("User with email {} found", email);
        return jdbcTemplate.queryForObject(FIND_BY_EMAIL, rowMapper, email);
    }

    @Override
    public int countAll() {
        log.info("Created a database call - counting the number of rows");
        return jdbcTemplate.getFetchSize();
    }
    private void mapUserToStatementData (UserDto userDto, PreparedStatement statement) throws SQLException {
        statement.setString(1, userDto.getFirstName());
        statement.setString(2, userDto.getLastName());
        statement.setString(3, userDto.getEmail());
        statement.setString(4, userDto.getPassword());
        statement.setString(5, (userDto.getRole()).name());
        log.info("Object prepared for transfer to the database");
    }
}
