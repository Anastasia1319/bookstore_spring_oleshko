package com.belhard.bookstore.data.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    public static ConfigManager INSTANCE = new ConfigManager();
    private static final String PATH_TO_PROPERTIES = "/config.properties";
    private static final Logger log = LogManager.getLogger(ConfigManager.class);
    private Properties properties;

    private ConfigManager() {
        try (InputStream in = getClass().getResourceAsStream(PATH_TO_PROPERTIES)) {
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            log.error("Failed to read config.properties", e);
            throw new RuntimeException(e);
        }
    }

    public String getConfig(String key) {
        log.info("Returned properties");
        return properties.getProperty(key);
    }
}
