package by.belotskiy.movie_star.model.entity;

public class Like {
    private int userId;
    private int reviewId;
    private boolean isLikeOrDislike;

    public Like(int userId, int reviewId, boolean isLikeOrDislike) {
        this.userId = userId;
        this.reviewId = reviewId;
        this.isLikeOrDislike = isLikeOrDislike;
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

    public boolean isLikeOrDislike() {
        return isLikeOrDislike;
    }

    public void setLikeOrDislike(boolean likeOrDislike) {
        isLikeOrDislike = likeOrDislike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return getUserId() == like.getUserId()
                && getReviewId() == like.getReviewId()
                && isLikeOrDislike() == like.isLikeOrDislike();
    }

    @Override
    public int hashCode() {
        int result =  isLikeOrDislike() ? 1 : 0;
        result = 31 * result + getReviewId();
        result = 31 * result + getUserId();
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Like{");
        sb.append("userId=").append(userId);
        sb.append(", reviewId=").append(reviewId);
        sb.append(", isLikeOrDislike=").append(isLikeOrDislike);
        sb.append('}');
        return sb.toString();
    }
}
