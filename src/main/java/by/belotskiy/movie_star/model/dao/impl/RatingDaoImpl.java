package by.belotskiy.movie_star.model.dao.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.dao.RatingDao;
import by.belotskiy.movie_star.model.entity.Rating;

public class RatingDaoImpl implements RatingDao {
    @Override
    public boolean isExists(Rating rating) throws DaoException {

        return false;
    }

    @Override
    public boolean update(Rating rating) throws DaoException {
        return false;
    }

    @Override
    public boolean save(Rating rating) throws DaoException {
        return false;
    }

    @Override
    public double calcAverageRating(int movieId) throws DaoException {
        return 0;
    }
}
