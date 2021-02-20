package by.belotskiy.movie_star.model.dao.query;

public class LikeQuery {

    private static final String LIKES_TABLE = "movie_star_db.likes";

    private LikeQuery(){}

    public static final String COUNT_LIKES__AND_DISLIKES =
            "(SELECT COUNT(*) FROM " + LIKES_TABLE + " WHERE review_id = ? AND is_like = 1) " +
                    "UNION (SELECT COUNT(*) FROM " +  LIKES_TABLE + " WHERE review_id = ? " +
                    "AND is_like = 0)";

    public static final String UPDATE_LIKE = "UPDATE " + LIKES_TABLE +
            " SET is_like = ? WHERE (user_id = ?) AND (review_id = ?)";

    public static final String SAVE_LIKE = "INSERT INTO " + LIKES_TABLE +
            " (user_id, movie_id, is_like) VALUES(?, ?, ?)";

}
