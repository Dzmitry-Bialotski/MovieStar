package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> findReviewsForAdmin() throws ServiceException;

    List<Review> findAllReviewsByMovieId(int movieId) throws ServiceException;

    Optional<Review> findReview(int reviewId) throws ServiceException;

    boolean updateReview(Review review) throws ServiceException;

    boolean addReview(Review review) throws ServiceException;
}
