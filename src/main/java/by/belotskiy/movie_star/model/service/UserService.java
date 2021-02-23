package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Interface provides actions on users
 *
 * @author Dmitriy Belotskiy
 */
public interface UserService {

    /**
     * login user
     *
     * @param login user login
     * @param password user password
     * @return optional value of user if login is successful
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<User> login(String login, String password) throws ServiceException;

    /**
     * register user
     *
     * @param login user login
     * @param password user password
     * @param passwordConfirmed password confirm
     * @return true if register successful, false, otherwise
     * @throws ServiceException if an error occurs while processing.
     */
    boolean register(String login, String password, String passwordConfirmed) throws ServiceException;

    /**
     * Finds user by id
     *
     * @param id user id
     * @return optional value of user.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<User> findUser(int id) throws ServiceException;

    /**
     * Finds user with cookies
     *
     * @param login user login
     * @param userHash user hash
     * @return optional value of user.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<User> findUserWithCookies(String login, String userHash) throws ServiceException;

    /**
     * update user
     *
     * @param user user entity
     * @return true if register successful, false, otherwise
     * @throws ServiceException if an error occurs while processing.
     */
    boolean updateUser(User user) throws ServiceException;

    /**
     * confirm email
     *
     * @param userId id of user
     * @param token for confirmation
     * @return optional value of user.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<User> confirmEmail(int userId, String token) throws ServiceException;

    /**
     * Finds all users
     *
     * @return list of users.
     * @throws ServiceException if an error occurs while processing.
     */
    List<User> findALlUsers() throws ServiceException;

}
