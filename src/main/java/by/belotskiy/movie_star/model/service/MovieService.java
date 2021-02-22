package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.util.SearchCriteria;

import javax.mail.search.SearchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieService {

    List<Movie> findALlMovies(int page, int count) throws ServiceException;

    Optional<Movie> findMovie(int movieID) throws ServiceException;

    boolean updateMovie(Movie movie) throws ServiceException;

    boolean addMovie(Movie movie) throws ServiceException;

    boolean deleteMovie(int movieId) throws ServiceException;

    List<Movie> findALlMoviesForAdmin() throws ServiceException;

    List<Movie> searchMovies(Map<SearchCriteria, String> searchMap, int page, int count) throws ServiceException;

}
