package by.belotskiy.movie_star.model.dao.impl;

import by.belotskiy.movie_star.exception.ConnectionPoolException;
import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.dao.UserDao;
import by.belotskiy.movie_star.model.entity.BaseEntity;
import by.belotskiy.movie_star.model.entity.Role;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final String INSERT_USER_STATEMENT =
            "INSERT INTO movie_star_db.users(id, login, email, password_hash, role, avatar_path, email_confirmed) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?);";



    @Override
    public List<User> findAll(int start, int end, String sortBy, boolean reverse) throws DaoException {
        return null;
    }

    @Override
    public Optional<User> findById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean save(BaseEntity entity) throws DaoException {
        User user = (User)entity;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_USER_STATEMENT);
            statement.setString(1, Integer.toString(user.getId()));
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPasswordHash());
            statement.setString(5, user.getRole().toString());
            statement.setString(6, user.getAvatar_path());
            statement.setString(7, ((Boolean)user.isEmailConfirmed()).toString());
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
    public boolean update(BaseEntity entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }
}
