package by.belotskiy.movie_star.service.impl;

import by.belotskiy.movie_star.dao.MovieDao;
import by.belotskiy.movie_star.dao.impl.MovieDaoMySql;
import by.belotskiy.movie_star.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private static MovieServiceImpl instance;

    private MovieServiceImpl() { }

    public static MovieServiceImpl getInstance() {
        if (instance == null) {
            instance = new MovieServiceImpl();
        }
        return instance;
    }

    private final MovieDao movieDao = MovieDaoMySql.getInstance();
}