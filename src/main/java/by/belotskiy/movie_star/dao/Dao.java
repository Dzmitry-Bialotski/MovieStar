package by.belotskiy.movie_star.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.BaseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Dao<T extends BaseEntity>  {
    Optional<T> findById(int id) throws DaoException;
    List<T> findAll() throws DaoException;
    List<T> findAllByCriteria(Map<String, String> criteria) throws DaoException;
    List<T> findWithLimits(int offset, int limit) throws DaoException;
    boolean save(BaseEntity entity) throws DaoException;
    boolean update(BaseEntity entity) throws DaoException;
    boolean delete(int id) throws DaoException;
    int findCount() throws DaoException;
}
