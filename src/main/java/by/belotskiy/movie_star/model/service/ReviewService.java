package by.belotskiy.movie_star.model.service;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Review;

import java.util.List;
import java.util.Optional;

/**
 * Interface provides actions on reviews
 *
 * @author Dmitriy Belotskiy
 */
public interface ReviewService {

    /**
     * Finds all review
     *
     * @return list of users.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Review> findReviewsForAdmin() throws ServiceException;

    /**
     * Finds all reviews of the movie by movieId
     *
     * @param movieId id of movie entity
     * @return optional value of movie.
     * @throws ServiceException if an error occurs while processing.
     */
    List<Review> findAllReviewsByMovieId(int movieId) throws ServiceException;

    /**
     * Finds movie by id
     *
     * @param reviewId if of movie entity
     * @return optional value of review.
     * @throws ServiceException if an error occurs while processing.
     */
    Optional<Review> findReview(int reviewId) throws ServiceException;

    /**
     * Updates review in a datasource.
     *
     * @param review movie entity
     * @return true if review has been updated successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean updateReview(Review review) throws ServiceException;

    /**
     * Adds review in a datasource.
     *
     * @param review movie entity
     * @return true if review has been added successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean addReview(Review review) throws ServiceException;

    /**
     * deletes review in a datasource.
     *
     * @param reviewId id of movie entity
     * @return true if review has been deleted successfully, false otherwise.
     * @throws ServiceException if an error occurs while processing.
     */
    boolean deleteReview(int reviewId) throws ServiceException;
}
