package com.belhard.bookstore.data.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final Logger logger = LogManager.getLogger(DataSource.class);
    private static final String URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    private Connection connection;

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Is connected");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
