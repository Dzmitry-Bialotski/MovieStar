package by.belotskiy.movie_star.model.dao.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.dao.ReviewDao;
import by.belotskiy.movie_star.model.dao.query.ReviewQuery;
import by.belotskiy.movie_star.model.pool.DynamicConnectionPool;
import by.belotskiy.movie_star.util.DaoUtil;
import by.belotskiy.movie_star.model.entity.Review;
import by.belotskiy.movie_star.model.entity.enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReviewDaoImpl implements ReviewDao {

    private static final String REVIEW_ID = "review_id";

    @Override
    public List<Review> findALl() throws DaoException {
        return findAllByCriteria(new HashMap<>());
    }

    @Override
    public List<Review> findAllByCriteria(Map<String, String> criteria) throws DaoException {
        List<Review> reviews = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "";
        try {
            connection = DynamicConnectionPool.getInstance().provideConnection();
            query = DaoUtil.createQueryWithCriteria(ReviewQuery.FIND_ALL_REVIEW, criteria);
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int reviewId = resultSet.getInt(1);
                String text = resultSet.getString(2);
                String userLogin = resultSet.getString(3);
                String movieTitle = resultSet.getString(4);
                Status status = Status.valueOf(resultSet.getString(5));
                int userId = resultSet.getInt(6);
                int movieId = resultSet.getInt(7);
                Review review = new Review(reviewId,userLogin,null, text,0, status, movieTitle,
                        userId, movieId);
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement, resultSet);
        }
        return reviews;
    }

    @Override
    public Optional<Review> findById(int reviewId) throws DaoException {
        Optional<Review> optionalReview = Optional.empty();
        Map<String, String> criteria = new HashMap<>();
        criteria.put(REVIEW_ID, Integer.toString(reviewId));
        List<Review> reviews = findAllByCriteria(criteria);
        if (reviews.size() > 0) {
            optionalReview = Optional.of(reviews.get(0));
        }
        return optionalReview;
    }

    @Override
    public boolean update(Review review) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        String query = "";
        try{
            query = ReviewQuery.UPDATE_REVIEW;
            statement = connection.prepareStatement(query);
            statement.setString(1, review.getText());
            statement.setString(2, review.getStatus().toString());
            statement.setInt(3, review.getId());
            statement.executeUpdate();
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

    @Override
    public boolean save(Review review) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        String query = "";
        try{
            query = ReviewQuery.INSERT_REVIEW;
            statement = connection.prepareStatement(query);
            statement.setInt(1, review.getUserId());
            statement.setInt(2, review.getMovieId());
            statement.setString(3, review.getText());
            statement.setString(4, review.getStatus().toString());
            statement.executeUpdate();
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

    @Override
    public List<Review> findAllByMovieId(int movieId) throws DaoException {
        List<Review> reviews = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        ResultSet resultSet;
        String query = "";
        try{
            connection = DynamicConnectionPool.getInstance().provideConnection();
            query = ReviewQuery.FIND_BY_MOVIE_ID;
            statement = connection.prepareStatement(query);
            statement.setInt(1, movieId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int reviewId = resultSet.getInt(1);
                String text = resultSet.getString(2);
                String userLogin = resultSet.getString(3);
                String movieTitle = resultSet.getString(4);
                Status status = Status.valueOf(resultSet.getString(5));
                int userId = resultSet.getInt(6);
                movieId = resultSet.getInt(7);
                String avatar_path = resultSet.getString(8);
                Review review = new Review(reviewId, userLogin, avatar_path,text,
                        0, status, movieTitle,userId,movieId);
                reviews.add(review);
            }
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return reviews;
    }

    @Override
    public boolean delete(int reviewId) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        String query = "";
        try{
            query = ReviewQuery.DELETE_REVIEW_BY_ID;
            statement = connection.prepareStatement(query);
            statement.setInt(1, reviewId);
            statement.executeUpdate();
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }
}
