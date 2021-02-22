package by.belotskiy.movie_star.model.dao.impl;



import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.pool.DynamicConnectionPool;
import by.belotskiy.movie_star.model.dao.UserDao;
import by.belotskiy.movie_star.util.DaoUtil;
import by.belotskiy.movie_star.model.dao.query.UserQuery;
import by.belotskiy.movie_star.model.entity.BaseEntity;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.entity.enums.Role;
import by.belotskiy.movie_star.model.entity.enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDaoImpl implements UserDao {

    public static final String USER_ID = "user_id";
    public static final String USER_HASH = "user_hash";
    public static final String LOGIN = "login";

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
        List<User> users;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "";
        try {
            connection = DynamicConnectionPool.getInstance().provideConnection();
            query = DaoUtil.createQueryWithCriteria(UserQuery.FIND_ALL_USER_QUERY, criteria);
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            users = extractUsersFromQuery(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement, resultSet);
        }
        return users;
    }

    @Override
    public List<User> findWithLimits(int offset, int limit) throws DaoException {
        List<User> users;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "";
        try {
            connection = DynamicConnectionPool.getInstance().provideConnection();
            query = UserQuery.FIND_USER_WITH_LIMITS_QUERY;
            statement = connection.prepareStatement(query);
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();
            users = extractUsersFromQuery(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement, resultSet);
        }
        return users;
    }
    @Override
    public boolean save(BaseEntity entity) throws DaoException {
        User user = (User)entity;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UserQuery.INSERT_USER_QUERY);
            fillStatement(statement, user);
            statement.executeUpdate();
        } catch (SQLException  e) {
            throw new DaoException("Error executing query ", e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

    @Override
    public boolean update(BaseEntity entity) throws DaoException {
        User user = (User)entity;
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        try {
            statement = connection.prepareStatement(UserQuery.UPDATE_USER_QUERY);
            fillStatement(statement, user);
            statement.setInt(11, user.getId());
            statement.executeUpdate();
        } catch (SQLException  e) {
            throw new DaoException("Error executing query ", e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        try {
            statement = connection.prepareStatement(UserQuery.DELETE_USER_QUERY);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

    @Override
    public int findCount() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "";
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        try {

            statement = connection.prepareStatement(UserQuery.FIND_COUNT_OF_USERS_QUERY);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement, resultSet);
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

    private List<User> extractUsersFromQuery(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
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
        return users;
    }

    private void fillStatement(PreparedStatement statement, User user) throws SQLException {
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
    }
}
