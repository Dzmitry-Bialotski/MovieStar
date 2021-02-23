package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.Rating;

import java.util.List;

/**
 * Interface provides actions on ratings
 *
 * @author Dmitriy Belotskiy
 */
public interface RatingService {

    /**
     * Updates rating in a datasource.
     *
     * @param rating movie entity
     * @return true if rating has been updated successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean update(Rating rating) throws ServiceException;

    /**
     * Calculate rating for movie by movieId
     *
     * @param movieId id of movie
     * @return rating value
     * @throws ServiceException if an error occurs while processing.
     */
    double calcRating(int movieId) throws ServiceException;

    /**
     * Calculate rating for the list of movies
     *
     * @param movies list of movies
     * @return true if rating has been updated successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean provideMoviesWithRating(List<Movie> movies) throws ServiceException;

}
