package by.belotskiy.movie_star.model.service.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.dao.RatingDao;
import by.belotskiy.movie_star.model.dao.factory.DaoFactory;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.Rating;
import by.belotskiy.movie_star.model.service.RatingService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class RatingServiceImpl implements RatingService {
    private final RatingDao ratingDao = DaoFactory.getInstance().getRatingDao();

    @Override
    public boolean update(Rating rating) throws ServiceException {
        try {
            if(ratingDao.isExists(rating)){
                ratingDao.update(rating);
            }else{
                ratingDao.save(rating);
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public double calcRating(int movieId) throws ServiceException {
        double result;
        try {
            result = ratingDao.calcAverageRating(movieId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean provideMoviesWithRating(List<Movie> movies) throws ServiceException {
        Map<Integer, Double> movieRatings;
        try {
            movieRatings = ratingDao.provideAllRatings();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        for(Movie movie : movies){
            double rating = movieRatings.get(movie.getId());
            String formattedRating = new DecimalFormat("#0.00").format(rating);
            movie.setRating(formattedRating);
        }
        return true;
    }
}
