package by.belotskiy.movie_star.model.entity;

/**
 * Interface force to have an id.
 *
 * @author Dmitriy Belotskiy
 */
public interface Identified<T> {
    T getId();
    void setId(T id);
}
