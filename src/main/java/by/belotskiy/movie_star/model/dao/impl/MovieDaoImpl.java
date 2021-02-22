package by.belotskiy.movie_star.model.dao.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.dao.MovieDao;
import by.belotskiy.movie_star.model.pool.DynamicConnectionPool;
import by.belotskiy.movie_star.model.dao.query.MovieQuery;
import by.belotskiy.movie_star.util.DaoUtil;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.enums.Genre;
import by.belotskiy.movie_star.model.entity.enums.MovieType;
import by.belotskiy.movie_star.model.entity.enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MovieDaoImpl implements MovieDao {

    private static final String MOVIE_ID = "movie_id";

    @Override
    public List<Movie> findAll() throws DaoException {
        return findAllByCriteria(new HashMap<>());
    }

    @Override
    public List<Movie> findAllByCriteria(Map<String, String> criteria) throws DaoException {
        List<Movie> movies = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "";
        try {
            connection = DynamicConnectionPool.getInstance().provideConnection();
            query = DaoUtil.createQueryWithCriteria(MovieQuery.FIND_ALL_MOVIE, criteria);
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int movieId = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String country = resultSet.getString(3);
                int year = resultSet.getInt(4);
                Genre genre = Genre.valueOf(resultSet.getString(5));
                MovieType movieType = MovieType.valueOf(resultSet.getString(6));
                int ageCategory = resultSet.getInt(7);
                String description = resultSet.getString(8);
                String youtubeTrailer = resultSet.getString(9);
                String imagePath = resultSet.getString(10);
                Status status = Status.valueOf(resultSet.getString(11));
                Movie movie = new Movie(movieId, title, country, year, genre, movieType, ageCategory,
                        description, youtubeTrailer, status, imagePath);
                movies.add(movie);
            }
        } catch (SQLException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement, resultSet);
        }
        return movies;
    }

    @Override
    public Optional<Movie> findById(int movieId) throws DaoException {
        Optional<Movie> optionalMovie = Optional.empty();
        Map<String, String> criteria = new HashMap<>();
        criteria.put(MOVIE_ID, Long.toString(movieId));
        List<Movie> movies = findAllByCriteria(criteria);
        if (movies.size() > 0) {
            optionalMovie = Optional.of(movies.get(0));
        }
        return optionalMovie;
    }

    @Override
    public boolean update(Movie movie) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        String query = "";
        try{
            query = MovieQuery.UPDATE_MOVIE;
            statement = connection.prepareStatement(query);
            statement.setInt(1,movie.getId());
            statement.setString(2,movie.getTitle());
            statement.setString(3, movie.getCountry());
            statement.setInt(4,movie.getYear());
            statement.setString(5,movie.getGenre().toString());
            statement.setString(6,movie.getMovieType().toString());
            statement.setInt(7,movie.getAgeCategory());
            statement.setString(8,movie.getDescription());
            statement.setString(9,movie.getYoutubeTrailer());
            statement.setString(10,movie.getImagePath() );
            statement.setString(11,movie.getStatus().toString());
            statement.setInt(12,movie.getId());
          statement.executeUpdate();
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

    @Override
    public boolean save(Movie movie) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        String query = "";
        try{
            query = MovieQuery.INSERT_MOVIE;
            statement = connection.prepareStatement(query);
            statement.setString(1,movie.getTitle());
            statement.setString(2, movie.getCountry());
            statement.setInt(3,movie.getYear());
            statement.setString(4,movie.getGenre().toString());
            statement.setString(5,movie.getMovieType().toString());
            statement.setInt(6,movie.getAgeCategory());
            statement.setString(7,movie.getDescription());
            statement.setString(8,movie.getYoutubeTrailer());
            statement.setString(9,movie.getImagePath() );
            statement.setString(10,movie.getStatus().toString());
            statement.executeUpdate();
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

    @Override
    public boolean delete(int movieId) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        String query = "";
        try{
            query = MovieQuery.DELETE_MOVIE;
            statement = connection.prepareStatement(query);
            statement.setInt(1, movieId);
            statement.execute();
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }
}
