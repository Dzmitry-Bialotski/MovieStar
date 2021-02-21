package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> findALlMovies(int page, int count) throws ServiceException;

    Optional<Movie> findMovie(int movieID) throws ServiceException;

    boolean updateMovie(Movie movie) throws ServiceException;

    boolean addMovie(Movie movie) throws ServiceException;

    boolean deleteMovie(int movieId) throws ServiceException;

    List<Movie> findALlMoviesForAdmin() throws ServiceException;

}
