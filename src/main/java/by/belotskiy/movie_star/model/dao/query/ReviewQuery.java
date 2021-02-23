package by.belotskiy.movie_star.model.dao.query;

/**
 * Provides queries to work with Review entity
 *
 * @author Dmitriy Belotskiy
 */
public class ReviewQuery {

    public static final String MOVIES_TABLE = "movie_star_db.movies";
    public static final String USERS_TABLE = "movie_star_db.users";
    public static final String REVIEWS_TABLE = "movie_star_db.reviews";
    public static final String JOIN = " JOIN ";
    public static final String ON = " ON ";

    public static final String FIND_ALL_REVIEW =
            new StringBuilder().append("SELECT ").append(REVIEWS_TABLE).append(".review_id, ")
                    .append(REVIEWS_TABLE).append(".text, ").append(USERS_TABLE).append(".login, ")
                    .append(MOVIES_TABLE).append(".title, ").append(REVIEWS_TABLE).append(".status, ")
                    .append(REVIEWS_TABLE).append(".user_id, ").append(REVIEWS_TABLE).append(".movie_id ")
                    .append("FROM ").append(REVIEWS_TABLE).append(JOIN).append(USERS_TABLE).append(ON)
                    .append(USERS_TABLE).append(".user_id = ").append(REVIEWS_TABLE).append(".user_id ")
                    .append(JOIN).append(MOVIES_TABLE).append(ON).append(MOVIES_TABLE).append(".movie_id = ")
                    .append(REVIEWS_TABLE).append(".movie_id").toString();

    public static final String UPDATE_REVIEW =
            new StringBuilder().append("UPDATE ").append(REVIEWS_TABLE).append(" SET text = ?, status = ?")
                    .append(" WHERE (review_id = ?);").toString();

    public static final String INSERT_REVIEW = "INSERT INTO " + REVIEWS_TABLE + " (user_id, movie_id, text, status)" +
            " VALUES (?, ?, ?, ?);";

    public static final String FIND_BY_ID  = "SELECT review_id, user_id, movie_id, text, status FROM " + REVIEWS_TABLE +
            " WHERE review_id = ?;";

    public static final String FIND_BY_MOVIE_ID =
            new StringBuilder().append("SELECT ").append(REVIEWS_TABLE).append(".review_id, ").append(REVIEWS_TABLE)
                    .append(".text, ").append(USERS_TABLE).append(".login, ").append(MOVIES_TABLE).append(".title, ")
                    .append(REVIEWS_TABLE).append(".status, ").append(REVIEWS_TABLE).append(".user_id, ")
                    .append(REVIEWS_TABLE).append(".movie_id, ").append(USERS_TABLE).append(".avatar_path ")
                    .append("FROM ").append(REVIEWS_TABLE).append(JOIN)
                    .append(USERS_TABLE).append(ON).append(USERS_TABLE).append(".user_id = ").append(REVIEWS_TABLE)
                    .append(".user_id ").append(JOIN).append(MOVIES_TABLE).append(ON).append(MOVIES_TABLE)
                    .append(".movie_id = ").append(REVIEWS_TABLE).append(".movie_id").append(" WHERE ")
                    .append(REVIEWS_TABLE).append(".movie_id ").append(" = ?").toString();

    public static final String DELETE_REVIEW_BY_ID = "DELETE FROM " + REVIEWS_TABLE +
            " WHERE (review_id = ?)";
    private ReviewQuery() {}

}
