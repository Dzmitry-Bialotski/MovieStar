package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Rating;

public interface RatingService {
    boolean update(Rating rating) throws ServiceException;
    double calcRating(int movieId) throws ServiceException;
}
