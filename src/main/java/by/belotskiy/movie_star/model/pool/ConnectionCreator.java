package by.belotskiy.movie_star.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Create connection object.
 *
 * @author Dmitriy Belotskiy
 */
public class ConnectionCreator {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionCreator.class);
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_PATH = "database.properties";
    private static final String DRIVER_PROPERTY = "db.driver";
    private static final String URL_PROPERTY = "db.url";
    static {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(PROPERTIES_PATH);
            PROPERTIES.load(input);
            String driver = PROPERTIES.getProperty(DRIVER_PROPERTY);
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.FATAL, "Error during connection creation", e);
        }
    }

    private ConnectionCreator() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(PROPERTIES.getProperty(URL_PROPERTY), PROPERTIES);
    }
}

