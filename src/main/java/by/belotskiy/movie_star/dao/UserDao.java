package by.belotskiy.movie_star.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User>, ClosableDao {
    Optional<User> findByLogin(String login) throws DaoException;
    Optional<User> findByLoginAndUserHash(String login, String userHash) throws DaoException;
}
