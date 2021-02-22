package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.Rating;

import java.util.List;

public interface RatingService {
    boolean update(Rating rating) throws ServiceException;
    double calcRating(int movieId) throws ServiceException;
    boolean provideMoviesWithRating(List<Movie> movies) throws ServiceException;
}
