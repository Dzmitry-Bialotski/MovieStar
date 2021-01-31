package by.belotskiy.movie_star.dao;

import by.belotskiy.movie_star.exception.DaoException;
import by.belotskiy.movie_star.model.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends BaseEntity> extends CloseableDao{

    List<T> findAll(int start, int end, String sortBy, boolean reverse) throws DaoException;

    Optional<T> findById(int id) throws DaoException;

    boolean save(BaseEntity entity) throws DaoException;

    boolean update(BaseEntity entity) throws DaoException;

    boolean delete(int id) throws DaoException;
}
