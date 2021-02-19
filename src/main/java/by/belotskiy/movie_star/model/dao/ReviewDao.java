package by.belotskiy.movie_star.model.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.Review;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReviewDao {
    List<Review> findALl() throws DaoException;
    List<Review> findAllByCriteria(Map<String, String> criteria) throws DaoException;
    Optional<Review> findById(int reviewId) throws DaoException;
    boolean update(Review review) throws  DaoException;
    boolean save(Review review) throws  DaoException;
    List<Review> findAllByMovieId(int movieId) throws DaoException;
    boolean delete(int reviewId) throws DaoException;

}
