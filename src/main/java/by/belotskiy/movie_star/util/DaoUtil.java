package by.belotskiy.movie_star.util;

import by.belotskiy.movie_star.model.pool.DynamicConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Contains some helping methods used by dao objects
 *
 * @author Dmitriy Belotskiy
 */
public class DaoUtil {

    private static final Logger log = LogManager.getLogger(DaoUtil.class);
    private static final String EQUALS_STRING = " = ";
    private static final String AND_DELIMITER = " AND ";
    private static final char QUOTE = '\'';
    private static final String WHERE = " WHERE ";

    private DaoUtil(){}

    /**
     * Release all the resources used by dao
     *
     * @author Dmitriy Belotskiy
     */
    public static void releaseResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        releaseResources(connection, preparedStatement);
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.warn("Can't close result set", e);
            }
        }
    }
    /**
     * Release all the resources used by dao
     *
     * @author Dmitriy Belotskiy
     */
    public static void releaseResources(Connection connection, PreparedStatement preparedStatement) {
        DynamicConnectionPool.getInstance().releaseConnection(connection);
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.warn("Can't close result set", e);
            }
        }
    }
    /**
     * Adds "WHERE" conditions to the end of select statement
     *
     * @author Dmitriy Belotskiy
     */
    public static String createQueryWithCriteria(String queryStart, Map<String, String> criteria) {
        List<String> conditions = new ArrayList<>();
        Set<String> keys = criteria.keySet();
        for (String key : keys) {
            String condition = key + EQUALS_STRING + QUOTE + criteria.get(key) + QUOTE;
            conditions.add(condition);
        }
        StringJoiner query = new StringJoiner(AND_DELIMITER);
        for (String condition : conditions) {
            query.add(condition);
        }
        if (query.toString().isEmpty()) {
            return queryStart;
        }
        return queryStart + WHERE + query.toString();
    }
}
