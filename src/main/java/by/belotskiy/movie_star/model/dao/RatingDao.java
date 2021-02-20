package by.belotskiy.movie_star.model.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.Rating;

public interface RatingDao {

    boolean isExists(Rating rating) throws DaoException;

    boolean update(Rating rating) throws DaoException;

    boolean save(Rating rating) throws DaoException;

    double calcAverageRating(int movieId) throws DaoException;

}
