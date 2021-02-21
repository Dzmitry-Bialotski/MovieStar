package by.belotskiy.movie_star.model.service.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.dao.LikeDao;
import by.belotskiy.movie_star.model.dao.factory.DaoFactory;
import by.belotskiy.movie_star.model.entity.Like;
import by.belotskiy.movie_star.model.service.LikeService;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Optional;

public class LikeServiceImpl implements LikeService {

    private final LikeDao likeDao = DaoFactory.getInstance().getLikeDao();

    @Override
    public boolean update(Like like) throws ServiceException {
        try{
            if(likeDao.isExists(like)){
                likeDao.update(like);
            }else{
                likeDao.save(like);
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public MutablePair<Integer, Integer> calcLikesAndDislikes(int reviewId) throws ServiceException {
        MutablePair<Integer, Integer> result;
        try{
            result = likeDao.getLikesAndDislikes(reviewId);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Optional<Like> findLike(int userId, int reviewId) throws ServiceException {
        try {
            return likeDao.findLike(userId, reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteLike(int userId, int reviewId) throws ServiceException {
        try {
            return likeDao.deleteLike(userId, reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
