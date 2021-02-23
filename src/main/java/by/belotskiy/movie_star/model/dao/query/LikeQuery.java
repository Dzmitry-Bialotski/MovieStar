package by.belotskiy.movie_star.model.dao.query;

/**
 * Provides queries to work with Like entity
 *
 * @author Dmitriy Belotskiy
 */
public class LikeQuery {

    private static final String LIKES_TABLE = "movie_star_db.likes";

    private LikeQuery(){}

    public static final String COUNT_LIKES__AND_DISLIKES =
            "(SELECT COUNT(*) FROM " + LIKES_TABLE + " WHERE review_id = ? AND is_like = 1) " +
                    "UNION ALL (SELECT COUNT(*) FROM " +  LIKES_TABLE + " WHERE review_id = ? " +
                    "AND is_like = 0)";

    public static final String UPDATE_LIKE = "UPDATE " + LIKES_TABLE +
            " SET is_like = ? WHERE (user_id = ?) AND (review_id = ?)";

    public static final String SAVE_LIKE = "INSERT INTO " + LIKES_TABLE +
            " (user_id, review_id, is_like) VALUES(?, ?, ?)";

    public static final String SELECT_LIKE_BY_USER_ID_REVIEW_ID = "SELECT user_id, review_id, is_like FROM " + LIKES_TABLE
            + " WHERE user_id = ? AND review_id = ?";

    public static final String DELETE_LIKE = "DELETE FROM " + LIKES_TABLE +
            " WHERE user_id = ? AND review_id = ?";

}
