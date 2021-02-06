package by.belotskiy.movie_star.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public interface ClosableDao {

    default void close(Statement statement) {
        final Logger LOGGER = LogManager.getLogger(ClosableDao.class);
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Statement hasn't been closed");
            }
        }
    }

    default void close(Connection connection) {
        final Logger logger = LogManager.getLogger(this.getClass());
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Statement hasn't been closed");
            }
        }
    }

    default void rollback(Connection connection, Savepoint savepoint) {
        final Logger logger = LogManager.getLogger(this.getClass());
        if (connection != null && savepoint != null) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Rollback failed");
            }
        }
    }

    default void rollback(Connection connection) {
        final Logger logger = LogManager.getLogger(this.getClass());
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Rollback failed");
            }
        }
    }
}

