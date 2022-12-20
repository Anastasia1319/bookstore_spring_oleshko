package com.belhard.bookstore.data.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private static final Logger log = LogManager.getLogger(DataSource.class);
    private static final String PATH_TO_PROPERTIES = "/config.properties";

    private Connection connection;

    public Connection getConnection() {
        initConnection();
        return connection;
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            try (InputStream in = getClass().getResourceAsStream(PATH_TO_PROPERTIES)) {
                Properties properties = new Properties();
                properties.load(in);
                connection = DriverManager.getConnection(properties.getProperty("URL"), properties.getProperty("USER"),
                        properties.getProperty("PASSWORD"));
                log.info("Is connected");
            }
        } catch (SQLException e) {
            log.error("Database connection not created: ", e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            log.error("Not found Driver class for connection with PostgreSQL");
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Failed to read config.properties", e);
            throw new RuntimeException(e);
        }
    }
}
