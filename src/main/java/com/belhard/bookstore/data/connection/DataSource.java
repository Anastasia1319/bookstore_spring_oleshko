package com.belhard.bookstore.data.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final Logger log = LogManager.getLogger(DataSource.class);
    private static final String URL = "jdbc:postgresql://surus.db.elephantsql.com/llpjwimb";
    private static final String USER = "llpjwimb";
    private static final String PASSWORD = "J7F660-xGX74oV9lT1bGlB-IAs2Z33gf";
    private Connection connection;

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            log.info("Is connected");
            return connection;
        } catch (SQLException e) {
            log.error("Database connection not created: ", e);
            throw new RuntimeException(e);
        }
    }
}
