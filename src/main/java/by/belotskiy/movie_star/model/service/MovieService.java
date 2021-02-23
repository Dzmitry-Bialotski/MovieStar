package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.util.SearchCriteria;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface provides actions on movies
 *
 * @author Dmitriy Belotskiy
 */
public interface MovieService {

    /**
     * Finds unblocked movies
     *
     * @param page page number
     * @param count count of movies that one page contains
     * @return optional value of movie.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Movie> findALlMovies(int page, int count) throws ServiceException;

    /**
     * Finds movie by id
     *
     * @param movieID if of movie entity
     * @return optional value of movie.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<Movie> findMovie(int movieID) throws ServiceException;

    /**
     * Updates movie in a datasource.
     *
     * @param movie movie entity
     * @return true if movie has been updated successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean updateMovie(Movie movie) throws ServiceException;

    /**
     * Adds movie in a datasource.
     *
     * @param movie movie entity
     * @return true if movie has been added successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean addMovie(Movie movie) throws ServiceException;

    /**
     * Deletes movie in a datasource.
     *
     * @param movieId id of movie entity
     * @return true if movie has been deleted successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean deleteMovie(int movieId) throws ServiceException;

    /**
     * Deletes movie in a datasource.
     *
     * @return list of all movies
     * @throws ServiceException if an error occurs while processing.
     */
    List<Movie> findALlMoviesForAdmin() throws ServiceException;

    /**
     * Searches movies in a datasource.
     *
     * @param searchMap map of Search option key and value
     * @param page page number
     * @param count count of movies that one page contains
     * @return list of movies matching search criteria
     * @throws ServiceException if an error occurs while processing.
     */
    List<Movie> searchMovies(Map<SearchCriteria, String> searchMap, int page, int count) throws ServiceException;

}
