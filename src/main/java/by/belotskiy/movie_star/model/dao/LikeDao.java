package by.belotskiy.movie_star.model.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.Like;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Optional;

/**
 * Interface used for interactions with like table.
 *
 * @author Dmitriy Belotskiy
 */
public interface LikeDao {

    /**
     * calculate likes and dislikes count
     *
     * @param reviewId review id
     * @return pair of likes and dislikes count.
     * @throws DaoException if the database throws SQLException.
     */
    MutablePair<Integer, Integer> getLikesAndDislikes(int reviewId) throws DaoException;

    /**
     * updates like
     *
     * @param like like entity
     * @return true if like updated, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean update(Like like) throws DaoException;

    /**
     * saves like
     *
     * @param like like entity
     * @return true if like saved, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean save(Like like) throws DaoException;

    /**
     * check if like exists
     *
     * @param like like entity
     * @return true if like exists
     * @throws DaoException if the database throws SQLException.
     */
    boolean isExists(Like like) throws DaoException;

    /**
     * finds like
     *
     * @param userId id of owner
     * @param reviewId id of review
     * @return optional of like
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Like> findLike(int userId, int reviewId) throws DaoException;

    /**
     * deletes like
     *
     * @param userId id of owner
     * @param reviewId id of review
     * @return true if like deleted, false otherwise.
     * @throws DaoException if the database throws SQLException.
     */
    boolean deleteLike(int userId, int reviewId) throws DaoException;

}
