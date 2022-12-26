package com.belhard.bookstore.data.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataSource {
    private static final Logger log = LogManager.getLogger(DataSource.class);
    private static final String PATH_TO_PROPERTIES = "/application.properties";
    @Value("${db.remote.url}")
    private static String url;
    @Value("${db.remote.user}")
    private static String user;
    @Value("${db.remote.password}")
    private static String password;

    private Connection connection;

    public Connection getConnection() {
        initConnection();
        return connection;
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            log.info("Is connected");
        } catch (SQLException e) {
            log.error("Database connection not created: ", e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            log.error("Not found Driver class for connection with PostgreSQL");
            throw new RuntimeException(e);
        }
    }
}
