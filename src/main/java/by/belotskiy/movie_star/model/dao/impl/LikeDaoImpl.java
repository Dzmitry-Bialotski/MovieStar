package by.belotskiy.movie_star.model.dao.impl;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.dao.LikeDao;
import by.belotskiy.movie_star.model.dao.query.LikeQuery;
import by.belotskiy.movie_star.util.DaoUtil;
import by.belotskiy.movie_star.model.entity.Like;
import by.belotskiy.movie_star.model.pool.DynamicConnectionPool;
import org.apache.commons.lang3.tuple.MutablePair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LikeDaoImpl implements LikeDao {

    @Override
    public MutablePair<Integer, Integer> getLikesAndDislikes(int reviewId) throws DaoException{
        MutablePair<Integer, Integer> result = new MutablePair<>();
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        ResultSet resultSet = null;
        String query = "";
        try{
            query = LikeQuery.COUNT_LIKES__AND_DISLIKES;
            statement = connection.prepareStatement(query);
            statement.setInt(1, reviewId);
            statement.setInt(2, reviewId);
            resultSet = statement.executeQuery();
            resultSet.next();
            int likes = resultSet.getInt(1);
            resultSet.next();
            int dislikes = resultSet.getInt(1);
            result.setLeft(likes);
            result.setRight(dislikes);
        }catch (SQLException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement, resultSet);
        }
        return result;
    }

    @Override
    public boolean update(Like like) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        String query = "";
        try{
            query = LikeQuery.UPDATE_LIKE;
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, like.isLike());
            statement.setInt(2, like.getUserId());
            statement.setInt(3, like.getReviewId());
            statement.executeUpdate();
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

    @Override
    public boolean save(Like like) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        String query = "";
        try{
            query = LikeQuery.SAVE_LIKE;
            statement = connection.prepareStatement(query);
            statement.setInt(1, like.getUserId());
            statement.setInt(2, like.getReviewId());
            statement.setBoolean(3, like.isLike());
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

    @Override
    public boolean isExists(Like like) throws DaoException {
        boolean result;
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        ResultSet resultSet = null;
        String query = "";
        try{
            query = LikeQuery.SELECT_LIKE_BY_USER_ID_REVIEW_ID;
            statement = connection.prepareStatement(query);
            statement.setInt(1, like.getUserId());
            statement.setInt(2, like.getReviewId());
            resultSet = statement.executeQuery();
            result = resultSet.next();
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement, resultSet);
        }
        return result;
    }

    @Override
    public Optional<Like> findLike(int userId, int reviewId) throws DaoException {
        Optional<Like> optionalLike;
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        ResultSet resultSet = null;
        String query = "";
        try{
            query = LikeQuery.SELECT_LIKE_BY_USER_ID_REVIEW_ID;
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, reviewId);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int user_id = resultSet.getInt(1);
                int review_id = resultSet.getInt(2);
                boolean isLike = resultSet.getBoolean(3);
                Like like = new Like(user_id, review_id, isLike);
                optionalLike = Optional.of(like);
            }else {
                optionalLike = Optional.empty();
            }
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement, resultSet);
        }
        return optionalLike;
    }

    @Override
    public boolean deleteLike(int userId, int reviewId) throws DaoException {
        PreparedStatement statement = null;
        Connection connection = DynamicConnectionPool.getInstance().provideConnection();
        String query = "";
        try{
            query = LikeQuery.DELETE_LIKE;
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, reviewId);
            statement.executeUpdate();
        }catch (SQLException  e) {
            throw new DaoException("Error executing query " + query, e);
        } finally {
            DaoUtil.releaseResources(connection, statement);
        }
        return true;
    }

}
