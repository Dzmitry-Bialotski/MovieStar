package by.belotskiy.movie_star.model.pool;

import by.belotskiy.movie_star.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static ConnectionPool instance;
    public static ConnectionPool getInstance(){
        if(instance == null){
            instance= new ConnectionPool();
        }
        return instance;
    }
    private final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenConnections;
    private static final int DEFAULT_POOL_SIZE = 8;
    private static final int FATAL_CONNECTION_ERROR_NUMBER = 3;

    private ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenConnections = new ArrayDeque<>(DEFAULT_POOL_SIZE);
        int errorCounter = 0;
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionCreator.createConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.offer(proxyConnection);
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error during connection creating", e);
                errorCounter++;
            }
        }
        if (errorCounter >= FATAL_CONNECTION_ERROR_NUMBER) {
            LOGGER.log(Level.FATAL, errorCounter + " connection(s) hasn't been created");
            throw new RuntimeException(errorCounter + " connection(s) hasn't been created");
        }
        LOGGER.log(Level.INFO, "Connection pool has been filled");
    }

    public Connection getConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
            LOGGER.log(Level.DEBUG, "Connection has been given");
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ConnectionPoolException(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection
                && givenConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
            LOGGER.log(Level.DEBUG, "Connection has been released");
        } else {
            LOGGER.log(Level.ERROR, "Invalid connection to release");
        }
    }

    public void destroyPool() throws ConnectionPoolException {
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                ProxyConnection proxyConnection = freeConnections.take();
                proxyConnection.reallyClose();
            }
            LOGGER.log(Level.INFO, "Connection pool has been destroyed");
        } catch (InterruptedException | SQLException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ConnectionPoolException(e);
        } finally {
            deregisterDrivers();
        }
    }

    private void deregisterDrivers() throws ConnectionPoolException {
        try {
            while (DriverManager.getDrivers().hasMoreElements()) {
                Driver driver = DriverManager.getDrivers().nextElement();
                DriverManager.deregisterDriver(driver);
            }
            LOGGER.log(Level.INFO, "Drivers have been deregistered");
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }
}

