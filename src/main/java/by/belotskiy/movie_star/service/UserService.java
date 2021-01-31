package by.belotskiy.movie_star.service;

import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password) throws ServiceException;
    boolean register(String login, String password, String passwordConfirmed) throws ServiceException;
}
