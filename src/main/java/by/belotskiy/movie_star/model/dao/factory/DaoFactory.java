package by.belotskiy.movie_star.model.dao.factory;

import by.belotskiy.movie_star.model.dao.MovieDao;
import by.belotskiy.movie_star.model.dao.ReviewDao;
import by.belotskiy.movie_star.model.dao.UserDao;
import by.belotskiy.movie_star.model.dao.impl.MovieDaoImpl;
import by.belotskiy.movie_star.model.dao.impl.ReviewDaoImpl;
import by.belotskiy.movie_star.model.dao.impl.UserDaoImpl;

public class DaoFactory {

    private static volatile DaoFactory instance;

    private final UserDao userDao = new UserDaoImpl();

    private final MovieDao movieDao = new MovieDaoImpl();

    private final ReviewDao reviewDao = new ReviewDaoImpl();

    private DaoFactory() { }

    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    instance = new DaoFactory();
                }
            }
        }
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public MovieDao getMovieDao() {
        return movieDao;
    }

    public ReviewDao getReviewDao() {
        return reviewDao;
    }
}
