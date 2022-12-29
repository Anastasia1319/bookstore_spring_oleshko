package com.belhard.bookstore.data.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class DataSource {
    private static final Logger log = LogManager.getLogger(DataSource.class);
    private static final String PATH_TO_PROPERTIES = "/application.properties";
    /*@Value("${db.remote.url}")
    private static String url;
    @Value("${db.remote.user}")
    private static String user;
    @Value("${db.remote.password}")
    private static String password;*/

    private Connection connection;

    @Autowired
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
                connection = DriverManager.getConnection(properties.getProperty("db.remote.url"), properties.getProperty("db.remote.user"),
                        properties.getProperty("db.remote.password"));
                log.info("Is connected");
            }
        } catch (SQLException | IOException e) {
            log.error("Database connection not created: ", e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            log.error("Not found Driver class for connection with PostgreSQL");
            throw new RuntimeException(e);
        }
    }
}
