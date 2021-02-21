package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Like;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Optional;

public interface LikeService {
    boolean update(Like like) throws ServiceException;
    MutablePair<Integer, Integer> calcLikesAndDislikes(int reviewId) throws ServiceException;
    Optional<Like> findLike(int userId, int reviewId) throws ServiceException;
    boolean deleteLike(int userId, int reviewId) throws ServiceException;
}
