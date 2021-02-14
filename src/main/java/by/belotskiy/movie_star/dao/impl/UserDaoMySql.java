package by.belotskiy.movie_star.dao.impl;

import by.belotskiy.movie_star.dao.UserDao;
import by.belotskiy.movie_star.dao.pool.ConnectionPool;
import by.belotskiy.movie_star.dao.query.MySqlUserQuery;
import by.belotskiy.movie_star.exception.ConnectionPoolException;
import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.BaseEntity;
import by.belotskiy.movie_star.model.entity.enums.Role;
import by.belotskiy.movie_star.model.entity.enums.Status;
import by.belotskiy.movie_star.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDaoMySql implements UserDao {

    public static final String USER_ID = "user_id";
    public static final String USER_HASH = "user_hash";
    public static final String LOGIN = "login";
    private static final String EQUALS = " = ";
    private static final String AND = " AND ";
    private static final char QUOTE = '\'';
    private static final String WHERE = " WHERE ";
    private static UserDaoMySql INSTANCE;

    private UserDaoMySql(){ }
    public static UserDaoMySql getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UserDaoMySql();
        }
        return INSTANCE;
    }
    @Override
    public Optional<User> findById(int id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        Map<String, String> criteria = new HashMap<>();
        criteria.put(USER_ID, Long.toString(id));
        List<User> users = findAllByCriteria(criteria);
        if (users.size() > 0) {
            optionalUser = Optional.of(users.get(0));
        }
        return optionalUser;
    }

    @Override
    public List<User> findAll() throws DaoException {
        return findAllByCriteria(new HashMap<>());
    }

    @Override
    public List<User> findAllByCriteria(Map<String, String> criteria) throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            query = createQueryWithCriteria(MySqlUserQuery.FIND_ALL_USER_QUERY, criteria);
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password_hash = resultSet.getString(4);
                Role role = Role.valueOf(resultSet.getString(5).toUpperCase());
                String avatar_path = resultSet.getString(6);
                boolean email_confirmed = resultSet.getBoolean(7);
                String user_hash = resultSet.getString(8);
                String first_name = resultSet.getString(9);
                String second_name = resultSet.getString(10);
                Status status = Status.valueOf(resultSet.getString(11).toUpperCase());
                User user = new User(userId, login, email, password_hash, role, avatar_path,
                        email_confirmed, first_name, second_name, user_hash, status);
                users.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public List<User> findWithLimits(int offset, int limit) throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            query = MySqlUserQuery.FIND_USER_WITH_LIMITS_QUERY;
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password_hash = resultSet.getString(4);
                Role role = Role.valueOf(resultSet.getString(5).toUpperCase());
                String avatar_path = resultSet.getString(6);
                boolean email_confirmed = resultSet.getBoolean(7);
                String user_hash = resultSet.getString(8);
                String first_name = resultSet.getString(9);
                String second_name = resultSet.getString(10);
                Status status = Status.valueOf(resultSet.getString(11).toUpperCase());
                User user = new User(userId, login, email, password_hash, role, avatar_path,
                        email_confirmed, user_hash, first_name, second_name, status);
                users.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public boolean save(BaseEntity entity) throws DaoException {
        User user = (User)entity;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(MySqlUserQuery.INSERT_USER_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getAvatar_path());
            statement.setBoolean(6, user.isEmailConfirmed());
            statement.setString(7, user.getUserHash());
            statement.setString(8, user.getFirstName());
            statement.setString(9, user.getSecondName());
            statement.setString(10, user.getStatus().toString());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            close(statement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean update(BaseEntity entity) throws DaoException {
        User user = (User)entity;
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(MySqlUserQuery.UPDATE_USER_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getAvatar_path());
            statement.setBoolean(6, user.isEmailConfirmed());
            statement.setString(7, user.getUserHash());
            statement.setString(8, user.getFirstName());
            statement.setString(9, user.getSecondName());
            statement.setString(10, user.getStatus().toString());
            statement.setInt(11, user.getId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            close(statement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(MySqlUserQuery.DELETE_USER_QUERY);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return true;
    }

    @Override
    public int findCount() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(MySqlUserQuery.FIND_COUNT_OF_USERS_QUERY);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt(1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Map<String, String> criteria = new HashMap<>();
        criteria.put(LOGIN, login);
        List<User> users = findAllByCriteria(criteria);
        Optional<User> optionalUser = Optional.empty();
        if (users.size() > 0) {
            optionalUser = Optional.of(users.get(0));
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findByLoginAndUserHash(String login, String userHash) throws DaoException {
        Map<String, String> criteria = new HashMap<>();
        criteria.put(LOGIN, login);
        criteria.put(USER_HASH, userHash);
        List<User> users = findAllByCriteria(criteria);
        Optional<User> optionalUser = Optional.empty();
        if (users.size() > 0) {
            optionalUser = Optional.of(users.get(0));
        }
        return optionalUser;
    }

    private String createQueryWithCriteria(String queryStart, Map<String, String> criteria) {
        List<String> conditions = new ArrayList<>();
        Set<String> keys = criteria.keySet();
        for (String key : keys) {
            StringBuilder sb = new StringBuilder();
            String condition = sb.append(key).append(EQUALS).append(QUOTE).append(criteria.get(key)).append(QUOTE).toString();
            conditions.add(condition);
        }
        StringJoiner query = new StringJoiner(AND);
        for (String condition : conditions) {
            query.add(condition);
        }
        if (query.toString().isEmpty()) {
            return queryStart;
        }
        return queryStart + WHERE + query.toString();
    }
}
