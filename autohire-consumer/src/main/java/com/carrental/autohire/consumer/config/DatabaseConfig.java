package com.carrental.autohire.consumer.config;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static final String PROPERTY_FILE_PATH = "application.properties";

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_PATH)) {
            if (inputStream == null) {
                throw new SQLException("Unable to find property file: " + PROPERTY_FILE_PATH);
            }

            properties.load(inputStream);

            String url = properties.getProperty("datasource.url");
            String username = properties.getProperty("datasource.username");
            String password = properties.getProperty("datasource.password");

            return DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            throw new SQLException("Failed to load database properties from property file.", e);
        }
    }
}
