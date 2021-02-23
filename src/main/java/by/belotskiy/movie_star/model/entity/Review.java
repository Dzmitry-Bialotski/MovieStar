package by.belotskiy.movie_star.model.entity;

import by.belotskiy.movie_star.model.entity.enums.Status;

import java.io.Serializable;
import java.util.Objects;
/**
 * Entity of review.
 *
 * @author Dmitriy Belotskiy
 */
public class Review extends BaseEntity implements Serializable {
    private String userLogin;
    private String userAvatarPath;
    private String text;
    private String movieTitle;
    private int userId;
    private int movieId;
    private int likesNumber;

    private int likes;
    private int dislikes;

    public Review(String userLogin, String userAvatarPath, String text,
                  int likesNumber, Status status, int userId, int movieId) {
        this.userLogin = userLogin;
        this.userAvatarPath = userAvatarPath;
        this.text = text;
        this.likesNumber = likesNumber;
        this.setStatus(status);
        this.userId = userId;
        this.movieId = movieId;
    }

    public Review(int id, String userLogin, String userAvatarPath, String text,
                  int likesNumber, Status status, String movieTitle, int userId, int movieId) {
        super(id);
        this.userLogin = userLogin;
        this.userAvatarPath = userAvatarPath;
        this.text = text;
        this.likesNumber = likesNumber;
        this.movieTitle = movieTitle;
        this.setStatus(status);
        this.userId = userId;
        this.movieId = movieId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserAvatarPath() {
        return userAvatarPath;
    }

    public void setUserAvatarPath(String userAvatarPath) {
        this.userAvatarPath = userAvatarPath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return getLikesNumber() == review.getLikesNumber()
                && Objects.equals(getUserLogin(), review.getUserLogin())
                && Objects.equals(getUserAvatarPath(), review.getUserAvatarPath())
                && Objects.equals(getText(), review.getText());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (userAvatarPath != null ? userAvatarPath.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + likesNumber;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Review{");
        sb.append("userLogin='").append(userLogin).append('\'');
        sb.append(", userAvatarPath='").append(userAvatarPath).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", likesNumber=").append(likesNumber);
        sb.append('}');
        return sb.toString();
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
