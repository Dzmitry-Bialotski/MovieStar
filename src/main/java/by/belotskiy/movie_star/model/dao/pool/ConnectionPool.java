package by.belotskiy.movie_star.model.dao.pool;

import com.mysql.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static final Logger log = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_POOL_SIZE = 8;
    private static final ConnectionPool instance = new ConnectionPool();
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenAwayConnections;

    private ConnectionPool() {

        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            log.warn("Error while registering driver", e);
        }
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            Connection connection;
            try {
                connection = ConnectionCreator.createConnection();
            } catch (SQLException e) {
                log.fatal("Error while getting connection from driver manager", e);
                throw new RuntimeException("Error while getting connection from driver manager", e);
            }
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            freeConnections.offer(proxyConnection);
        }
        givenAwayConnections = new ArrayDeque<>();
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            log.fatal("Can't get connection form connection queue", e);
            throw new RuntimeException("Error while getting connection from queue", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            givenAwayConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            log.warn("Invalid connection object provided");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().close();
            } catch (InterruptedException e) {
                log.warn("Can't take connection from queue", e);
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                log.warn("Can't access database to deregister driver", e);
            }
        });
    }
}
