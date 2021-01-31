package by.belotskiy.movie_star.dao.impl;

import by.belotskiy.movie_star.exception.ConnectionPoolException;
import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.dao.UserDao;
import by.belotskiy.movie_star.model.creator.UserCreator;
import by.belotskiy.movie_star.model.entity.BaseEntity;
import by.belotskiy.movie_star.model.entity.Role;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl userDaoInstance;

    private UserDaoImpl(){

    }
    public static UserDaoImpl getInstance(){
        if(userDaoInstance == null){
            userDaoInstance= new UserDaoImpl();
        }
        return userDaoInstance;
    }

    @Override
    public List<User> findAll(int start, int end, String sortBy, boolean reverse) {
        return null;
    }

    @Override
    public Optional<User> findById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            String SELECT_USER_BY_ID_STATEMENT = "SELECT id, login, email, password_hash, role, avatar_path, email_confirmed " +
                    "FROM movie_star_db.users WHERE id = ?;";
            statement = connection.prepareStatement(SELECT_USER_BY_ID_STATEMENT);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                User user = parseUser(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return Optional.empty();
    }

    @Override
    public boolean save(BaseEntity entity) throws DaoException {
        User user = (User)entity;
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            String INSERT_USER_STATEMENT = "INSERT INTO movie_star_db.users(login, email, password_hash, role, avatar_path, email_confirmed) " +
                    "VALUES(?, ?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(INSERT_USER_STATEMENT);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getAvatar_path());
            statement.setBoolean(6, user.isEmailConfirmed());
            result = statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }

    @Override
    public boolean update(BaseEntity entity) throws DaoException {
        User user = (User)entity;
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            String UPDATE_USER_STATEMENT = "UPDATE movie_star_db.users SET login = ?, email = ?, " +
                    "password_hash = ?, role = ?, avatar_path = ?, email_confirmed = ? where id = ?;";
            statement = connection.prepareStatement(UPDATE_USER_STATEMENT);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getAvatar_path());
            statement.setBoolean(6, user.isEmailConfirmed());
            statement.setInt(7, user.getId());
            result = statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }

    @Override
    public boolean delete(int id) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            String DELETE_USER_STATEMENT = "DELETE FROM movie_star_db.users WHERE id = ?);";
            statement = connection.prepareStatement(DELETE_USER_STATEMENT);
            statement.setInt(1, id);
            result = statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return result;
    }

    @Override
    public boolean isUserExists(String login, String passwordHash) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            String SELECT_USER_BY_LOGIN_PASS_STATEMENT = "SELECT id, login, email, password_hash, role, avatar_path, email_confirmed " +
                    "FROM movie_star_db.users WHERE login = ? AND password_hash = ?;";
            statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_PASS_STATEMENT);
            statement.setString(1, login);
            statement.setString(2, passwordHash);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            String SELECT_USER_BY_LOGIN_STATEMENT = "SELECT id, login, email, password_hash, role, avatar_path, email_confirmed " +
                    "FROM movie_star_db.users WHERE login = ?;";
            statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_STATEMENT);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                User user = parseUser(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return Optional.empty();
    }

    private User parseUser(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt(1);
        String login = resultSet.getString(2);
        String email = resultSet.getString(3);
        String passwordHash = resultSet.getString(4);
        Role role = resultSet.getObject(5, Role.class);
        String avatar_path = resultSet.getString(6);
        boolean email_confirmed = resultSet.getBoolean(7);
        return UserCreator.createUser(id, login, email,
                passwordHash, role, avatar_path, email_confirmed);
    }
}
