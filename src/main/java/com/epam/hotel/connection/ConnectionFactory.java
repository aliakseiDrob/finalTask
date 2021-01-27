package com.epam.hotel.connection;

import com.epam.hotel.exception.ConnectionPoolException;
import com.epam.hotel.exception.DaoException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
    private static final String PROPERTY_FILE_NAME = "database.properties";
    private static final String URL_KEY_WORD = "url";
    private final Properties properties;
    private final String dataBaseUrl;

    public ConnectionFactory() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
            if (inputStream != null) {
                properties = new Properties();
                properties.load(inputStream);
            } else {
                throw new DaoException("property file '" + PROPERTY_FILE_NAME + "' not found in the classpath");
            }
            dataBaseUrl = properties.getProperty(URL_KEY_WORD);

        } catch (Exception e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

    //package access
    Connection create() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dataBaseUrl, properties);
        } catch (Exception e) {
            throw new ConnectionPoolException("Can't create Connection with database" + e.getMessage(), e);
        }
    }
}
