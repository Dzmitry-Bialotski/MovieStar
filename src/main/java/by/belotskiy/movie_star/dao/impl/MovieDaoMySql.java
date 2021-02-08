package by.belotskiy.movie_star.dao.impl;

import by.belotskiy.movie_star.dao.MovieDao;
import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.BaseEntity;
import by.belotskiy.movie_star.model.entity.Movie;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MovieDaoMySql implements MovieDao {

    private static MovieDaoMySql INSTANCE;

    private MovieDaoMySql(){ }
    public static MovieDaoMySql getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MovieDaoMySql();
        }
        return INSTANCE;
    }

    @Override
    public Optional<Movie> findById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Movie> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Movie> findAllByCriteria(Map<String, String> criteria) throws DaoException {
        return null;
    }

    @Override
    public List<Movie> findWithLimits(int offset, int limit) throws DaoException {
        return null;
    }

    @Override
    public boolean save(BaseEntity entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(BaseEntity entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        return false;
    }

    @Override
    public int findCount() throws DaoException {
        return 0;
    }
}
