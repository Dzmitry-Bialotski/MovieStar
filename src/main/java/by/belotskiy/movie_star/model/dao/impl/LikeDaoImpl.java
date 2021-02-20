package by.belotskiy.movie_star.model.dao.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.dao.LikeDao;
import by.belotskiy.movie_star.model.entity.Like;
import org.apache.commons.lang3.tuple.MutablePair;

public class LikeDaoImpl implements LikeDao {

    @Override
    public MutablePair<Integer, Integer> getLikesAndDislikes() {
        return null;
    }

    @Override
    public boolean update(Like like) throws DaoException {
        return false;
    }

    @Override
    public boolean save(Like like) throws DaoException {
        return false;
    }

    @Override
    public boolean isExists(Like like) throws DaoException {
        return false;
    }

}
