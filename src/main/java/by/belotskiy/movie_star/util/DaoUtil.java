package by.belotskiy.movie_star.util;

import by.belotskiy.movie_star.model.pool.DynamicConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DaoUtil {

    private static final Logger log = LogManager.getLogger(DaoUtil.class);
    private static final String EQUALS_STRING = " = ";
    private static final String AND_DELIMITER = " AND ";
    private static final char QUOTE = '\'';
    private static final String WHERE = " WHERE ";

    private DaoUtil(){}

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

    public static String createQueryWithCriteria(String queryStart, Map<String, String> criteria) {
        List<String> conditions = new ArrayList<>();
        Set<String> keys = criteria.keySet();
        for (String key : keys) {
            StringBuilder sb = new StringBuilder();
            String condition = sb.append(key).append(EQUALS_STRING).append(QUOTE).append(criteria.get(key)).append(QUOTE).toString();
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
