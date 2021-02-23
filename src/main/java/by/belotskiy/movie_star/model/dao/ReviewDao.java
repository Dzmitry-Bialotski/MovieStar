package by.belotskiy.movie_star.model.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.Review;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface used for interactions with review table.
 *
 * @author Dmitriy Belotskiy
 */
public interface ReviewDao {

    /**
     * finds all reviews
     *
     * @return list of reviews.
     * @throws DaoException if the database throws SQLException.
     */
    List<Review> findALl() throws DaoException;

    /**
     * finds all reviews by criteria
     *
     * @param criteria map for option key, value
     * @return list of reviews.
     * @throws DaoException if the database throws SQLException.
     */
    List<Review> findAllByCriteria(Map<String, String> criteria) throws DaoException;

    /**
     * finds review by id
     *
     * @param reviewId id of review
     * @return optional of review
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Review> findById(int reviewId) throws DaoException;

    /**
     * updates review
     *
     * @return true if review updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean update(Review review) throws  DaoException;

    /**
     * saves review
     *
     * @return true if review saved, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean save(Review review) throws  DaoException;

    /**
     * finds all reviews of movie
     *
     * @param movieId id of movie
     * @return list of reviews.
     * @throws DaoException if the database throws SQLException.
     */
    List<Review> findAllByMovieId(int movieId) throws DaoException;

    /**
     * deletes review
     *
     * @return true if review deleted, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean delete(int reviewId) throws DaoException;

}
