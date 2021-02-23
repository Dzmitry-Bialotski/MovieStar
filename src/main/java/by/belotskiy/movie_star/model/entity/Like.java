package by.belotskiy.movie_star.model.entity;

/**
 * Entity of like.
 *
 * @author Dmitriy Belotskiy
 */
public class Like {
    private int userId;
    private int reviewId;
    private boolean isLike;

    public Like(int userId, int reviewId, boolean isLike) {
        this.userId = userId;
        this.reviewId = reviewId;
        this.isLike = isLike;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean likeOrDislike) {
        isLike = likeOrDislike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return getUserId() == like.getUserId()
                && getReviewId() == like.getReviewId()
                && isLike() == like.isLike();
    }

    @Override
    public int hashCode() {
        int result =  isLike() ? 1 : 0;
        result = 31 * result + getReviewId();
        result = 31 * result + getUserId();
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Like{");
        sb.append("userId=").append(userId);
        sb.append(", reviewId=").append(reviewId);
        sb.append(", isLikeOrDislike=").append(isLike);
        sb.append('}');
        return sb.toString();
    }
}
