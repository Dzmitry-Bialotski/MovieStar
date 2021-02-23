package by.belotskiy.movie_star.model.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.BaseEntity;
import by.belotskiy.movie_star.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface used for interactions with user table.
 *
 * @author Dmitriy Belotskiy
 */
public interface UserDao {

    /**
     * finds user by id
     *
     * @param id user entity
     * @return optional of user
     * @throws DaoException if the database throws SQLException.
     */
    Optional<User> findById(int id) throws DaoException;

    /**
     * finds all users
     *
     * @return list of users
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findAll() throws DaoException;

    /**
     * finds all users with criteria
     *
     * @param criteria map of criteria
     * @return list of users
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findAllByCriteria(Map<String, String> criteria) throws DaoException;

    /**
     * finds all users with limits
     *
     * @param offset query offset
     * @param limit query limit
     * @return list of users
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findWithLimits(int offset, int limit) throws DaoException;

    /**
     * saves user
     *
     * @param entity user entity
     * @return true if user saved, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean save(BaseEntity entity) throws DaoException;

    /**
     * updates user
     *
     * @param entity user entity
     * @return true if user updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean update(BaseEntity entity) throws DaoException;

    /**
     * deletes user by id
     *
     * @param id user entity
     * @return true if user deleted, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean delete(int id) throws DaoException;

    /**
     * calculate count of users
     *
     * @return count of users
     * @throws DaoException if the database throws SQLException.
     */
    int findCount() throws DaoException;

    /**
     * find user by login
     *
     * @param login user login
     * @return optional of user
     * @throws DaoException if the database throws SQLException.
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * find user by login and user hash
     *
     * @param login user login
     * @param userHash user hash
     * @return optional of user
     * @throws DaoException if the database throws SQLException.
     */
    Optional<User> findByLoginAndUserHash(String login, String userHash) throws DaoException;

}
