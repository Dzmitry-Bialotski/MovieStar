package by.belotskiy.movie_star.model.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.Like;
import org.apache.commons.lang3.tuple.MutablePair;

public interface LikeDao {
    MutablePair<Integer, Integer> getLikesAndDislikes() throws DaoException;
    boolean update(Like like) throws DaoException;
    boolean save(Like like) throws DaoException;
    boolean isExists(Like like) throws DaoException;
}
