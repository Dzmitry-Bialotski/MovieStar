package by.belotskiy.movie_star.model.service.factory;

import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.RatingService;
import by.belotskiy.movie_star.model.service.ReviewService;
import by.belotskiy.movie_star.model.service.UserService;
import by.belotskiy.movie_star.model.service.impl.MovieServiceImpl;
import by.belotskiy.movie_star.model.service.impl.RatingServiceImpl;
import by.belotskiy.movie_star.model.service.impl.ReviewServiceImpl;
import by.belotskiy.movie_star.model.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static volatile ServiceFactory instance;

    private final UserService userService = new UserServiceImpl();

    private final MovieService movieService = new MovieServiceImpl();

    private final ReviewService reviewService = new ReviewServiceImpl();

    private final RatingService ratingService = new RatingServiceImpl();

    private ServiceFactory() { }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (ServiceFactory.class) {
                if (instance == null) {
                    instance = new ServiceFactory();
                }
            }
        }
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public RatingService getRatingService(){
        return  ratingService;
    }
}
