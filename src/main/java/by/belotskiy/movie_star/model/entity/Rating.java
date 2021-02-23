package by.belotskiy.movie_star.model.entity;

import java.io.Serializable;

/**
 * Entity of rating.
 *
 * @author Dmitriy Belotskiy
 */
public class Rating implements Serializable {
    private int value;
    private int userId;
    private int movieId;

    public Rating(int value, int userId, int movieId) {
        this.value = value;
        this.userId = userId;
        this.movieId = movieId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return  getValue() == rating.getValue()
                && getUserId() == rating.getUserId()
                && getMovieId() == rating.getMovieId();
    }

    @Override
    public int hashCode() {
        int result = getValue();
        result = 31 * result + getMovieId();
        result = 31 * result + getUserId();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rating{");
        sb.append("value=").append(value);
        sb.append(", userId=").append(userId);
        sb.append(", movieId=").append(movieId);
        sb.append('}');
        return sb.toString();
    }
}
