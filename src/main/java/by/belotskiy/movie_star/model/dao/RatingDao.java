package by.belotskiy.movie_star.model.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.Rating;

import java.util.Map;

/**
 * Interface used for interactions with rating table.
 *
 * @author Dmitriy Belotskiy
 */
public interface RatingDao {
    /**
     * Checks if rating exists.
     *
     * @return true if rating exists, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean isExists(Rating rating) throws DaoException;
    /**
     * Updates rating
     *
     * @return true if rating updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean update(Rating rating) throws DaoException;
    /**
     * Saves rating
     *
     * @return true if rating saved, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean save(Rating rating) throws DaoException;
    /**
     * calculate average rating of movie
     *
     * @param movieId id of movie
     * @return rating value.
     * @throws DaoException if the database throws SQLException.
     */
    double calcAverageRating(int movieId) throws DaoException;
    /**
     * calculate ratings of all movies.
     *
     * @return map where key - movieID, value - rating pf movie.
     * @throws DaoException if the database throws SQLException.
     */
    Map<Integer, Double> provideAllRatings() throws DaoException;

}
