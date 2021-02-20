package by.belotskiy.movie_star.model.dao.query;

public class RatingQuery {
    private RatingQuery(){}

    private static final String RATINGS_TABLE = "movie_star_db.ratings";

    public static final String SELECT_RATING_BY_USER_ID_MOVIE_ID = "SELECT user_id, movie_id, value FROM " + RATINGS_TABLE
            + " WHERE user_id = ? AND movie_id = ?";

    public static final String SELECT_AVG_RATING_BY_MOVIE_ID =
            "SELECT AVG(value) FROM " + RATINGS_TABLE + " WHERE movie_id = ?";

    public static final String UPDATE_RATING = "UPDATE " + RATINGS_TABLE +
            " SET value = ? WHERE (movie_id = ?) AND (user_id = ?)";

    public static final String INSERT_RATING = "INSERT INTO " + RATINGS_TABLE +
            " (user_id, movie_id, value) VALUES(?, ?, ?)";

}
