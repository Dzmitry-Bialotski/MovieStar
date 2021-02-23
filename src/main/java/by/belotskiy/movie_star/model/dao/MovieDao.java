package by.belotskiy.movie_star.model.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.Movie;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface used for interactions with movie table.
 *
 * @author Dmitriy Belotskiy
 */
public interface MovieDao {

    /**
     * finds all movies
     *
     * @return list pf movie
     * @throws DaoException if the database throws SQLException.
     */
    List<Movie> findAll() throws DaoException;

    /**
     * finds movie by id
     *
     * @param criteria map of search options
     * @return optional of movie
     * @throws DaoException if the database throws SQLException.
     */
    List<Movie> findAllByCriteria(Map<String, String> criteria) throws DaoException;

    /**
     * finds movie by id
     *
     * @param movieId movie id
     * @return optional pf movie
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Movie> findById(int movieId) throws DaoException;

    /**
     * updates movie
     *
     * @param movie movie entity
     * @return true if movie updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean update(Movie movie) throws  DaoException;

    /**
     * saves movie
     *
     * @param movie movie entity
     * @return true if movie saved, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean save(Movie movie) throws  DaoException;

    /**
     * deletes movie
     *
     * @param movieId movie id
     * @return true if movie deleted, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean delete(int movieId) throws DaoException;

}
