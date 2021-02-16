package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findALlMovies() throws ServiceException;
    Optional<Movie> findMovie(int movieID) throws ServiceException;
    boolean updateMovie(Movie movie) throws ServiceException;
}
