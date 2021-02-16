package by.belotskiy.movie_star.model.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.Review;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieDao {
    List<Movie> findAll() throws DaoException;
    List<Movie> findAllByCriteria(Map<String, String> criteria) throws DaoException;
    Optional<Movie> findById(int movieId) throws DaoException;
    boolean update(Movie movie) throws  DaoException;
    boolean save(Movie movie) throws  DaoException;
}
