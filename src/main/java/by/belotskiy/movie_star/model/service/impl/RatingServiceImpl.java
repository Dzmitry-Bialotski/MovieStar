package by.belotskiy.movie_star.model.service.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.dao.RatingDao;
import by.belotskiy.movie_star.model.dao.factory.DaoFactory;
import by.belotskiy.movie_star.model.entity.Rating;
import by.belotskiy.movie_star.model.service.RatingService;

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
}
