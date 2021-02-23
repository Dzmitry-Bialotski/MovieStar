package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Like;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Optional;
/**
 * Interface provides actions on likes
 *
 * @author Dmitriy Belotskiy
 */
public interface LikeService {

    /**
     * Updates like in a datasource.
     *
     * @param like movie entity
     * @return true if movie has been updated successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean update(Like like) throws ServiceException;

    /**
     * Calculate likes and dislikes count for review
     *
     * @param reviewId id of review
     * @return pair of likes and dislikes count
     * @throws ServiceException if an error occurs while processing.
     */
    MutablePair<Integer, Integer> calcLikesAndDislikes(int reviewId) throws ServiceException;

    /**
     * Finds like by id
     *
     * @param userId id of user who has like
     * @param reviewId id of review
     * @return optional value of like.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<Like> findLike(int userId, int reviewId) throws ServiceException;

    /**
     * Deletes movie in a datasource.
     *
     * @param userId id of user who has like
     * @param reviewId id of review
     * @return true if movie has been deleted successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean deleteLike(int userId, int reviewId) throws ServiceException;

}
