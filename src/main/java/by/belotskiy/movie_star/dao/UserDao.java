package by.belotskiy.movie_star.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User>{
    boolean isLoginExists(String login) throws DaoException;
    boolean isUserExists(String login, String passwordHash) throws DaoException;
    Optional<User> findByLogin(String login) throws DaoException;
}
