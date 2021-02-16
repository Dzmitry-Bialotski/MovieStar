package by.belotskiy.movie_star.model.service.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.dao.MovieDao;
import by.belotskiy.movie_star.model.dao.factory.DaoFactory;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.service.MovieService;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao = DaoFactory.getInstance().getMovieDao();

    @Override
    public List<Movie> findALlMovies() throws ServiceException {
        List<Movie> movies;
        try {
            movies = movieDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return movies;
    }

    @Override
    public Optional<Movie> findMovie(int movieId) throws ServiceException {
        Optional<Movie> optionalMovie;
        try {
            optionalMovie = movieDao.findById(movieId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalMovie;
    }

    @Override
    public boolean updateMovie(Movie movie) throws ServiceException {
        boolean result;
        try{
            result = movieDao.update(movie);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return result;
    }
}