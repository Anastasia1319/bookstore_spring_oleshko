package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.connection.DataSource;
import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.entity.Role;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    private static final String UPDATE = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, role_id = (SELECT r.role_id FROM role r WHERE r.name_role = ?)";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE user_id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) FROM users";
    private final DataSource dataSource;
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            log.info("Connected to URL");
            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));
            }
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException(e);
        }
        log.info("Created a list of users matching the search criteria");
        return users;
    }

    @Override
    public User create(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            log.info("Connected to URL");
            mapUserToStatementData(user, statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                log.info("User {} created", user);
                return findById(id);
            }
            log.warn("User {} wasn't created", user);
            throw new NotFoundException("Couldn't create user: " + user);
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("User " + user + "was not created", e);
        }
    }
    public User findById(Long id) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            log.info("Connected to URL");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't find user with id: " + id, e);
        }
        log.info("User with id {} found: {}", id, user);
        return user;
    }

    @Override
    public User update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            log.info("Connected to URL");
            mapUserToStatementData(user, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't update user: " + user, e);
        }
        log.info("User {} was update", user);
        return findByEmail(user.getEmail());
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            log.info("Connected to URL");
            statement.setLong(1, id);
            boolean deletedResult = (statement.executeUpdate() == 1);
            if (deletedResult) {
                log.info("User with id {} was delete", id);
            } else {
                log.info("User with id {} was not delete", id);
            }
            return deletedResult;
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't delete user with id: " + id, e);
        }
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL)) {
            log.info("Connected to URL");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = mapResultSetToUser(resultSet);
                log.info("User matching search criteria: {}", user);
            }
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException("Couldn't find user with email: " + email, e);
        }
        return user;
    }

    @Override
    public int countAll() {
        int count;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_ALL)) {
            log.info("Connected to URL");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            log.error("Database access error", e);
            throw new RuntimeException(e);
        }
        log.info("Number of objects in the database: {}", count);
        return count;
    }
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Role.valueOf(resultSet.getString("name_role")));
        log.info("Created a user based on the results from the database");
        return user;
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
