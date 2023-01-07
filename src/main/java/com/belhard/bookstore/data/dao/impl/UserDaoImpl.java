package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.exceptions.NotUpdateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
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
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }


    @Override
    public List<User> findAll() {
        log.info("Created a list of users matching the search criteria");
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public User create(User user) {
        log.info("Object creation method called");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE, new String[]{"user_id"});
            mapUserToStatementData(user, statement);
            return statement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::findById)
                .orElseThrow();
    }
    public User findById(Long id) {
        log.info("User with id {} found", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, rowMapper, id);
    }

    @Override
    public User update(User user) {
        log.info("Trying to update a row with a user in the database");
        int rowsUpdated = jdbcTemplate.update(UPDATE, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), user.getRole().name(), user.getId());
        if (rowsUpdated == 0) {
            log.warn("Updated rows (users): 0");
            throw new NotUpdateException("Couldn't update user: {}" + user);
        }
        return findById(user.getId());
    }

    @Override
    public boolean delete(Long id) {
        log.info("Trying to delete a row with a user in the database");
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, id);
        if (rowsUpdated == 0) {
            log.warn("Updated rows on deletion (users): 0");
            throw new NotUpdateException("Couldn't delete user with id: " + id);
        }
        return rowsUpdated == 1;
    }

    @Override
    public User findByEmail(String email) {
        log.info("User with email {} found", email);
        return jdbcTemplate.queryForObject(FIND_BY_EMAIL, rowMapper, email);
    }

    @Override
    public int countAll() {
        log.info("Created a database call - counting the number of rows");
        return jdbcTemplate.getFetchSize();
    }
    private void mapUserToStatementData (User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        statement.setString(5, (user.getRole()).name());
        log.info("Object prepared for transfer to the database");
    }

}
