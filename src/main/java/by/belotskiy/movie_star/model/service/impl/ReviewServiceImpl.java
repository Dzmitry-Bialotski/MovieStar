package by.belotskiy.movie_star.model.service.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.dao.ReviewDao;
import by.belotskiy.movie_star.model.dao.factory.DaoFactory;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.Review;
import by.belotskiy.movie_star.model.service.ReviewService;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao = DaoFactory.getInstance().getReviewDao();

    @Override
    public List<Review> findReviewsForAdmin() throws ServiceException {
        List<Review> reviews;
        try {
            reviews = reviewDao.findALl();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }

    @Override
    public List<Review> findAllReviewsByMovieId(int movieId) throws ServiceException {
        List<Review> reviews;
        try {
            reviews = reviewDao.findAllByMovieId(movieId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }

    @Override
    public Optional<Review> findReview(int reviewId) throws ServiceException {
        Optional<Review> optionalReview;
        try {
            optionalReview = reviewDao.findById(reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalReview;
    }

    @Override
    public boolean updateReview(Review review) throws ServiceException {
        boolean result;
        try{
            result = reviewDao.update(review);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean addReview(Review review) throws ServiceException {
        boolean result;
        try{
            result = reviewDao.save(review);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }
}
