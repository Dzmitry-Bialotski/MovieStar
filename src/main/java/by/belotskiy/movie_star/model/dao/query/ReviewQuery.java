package by.belotskiy.movie_star.model.dao.query;

public class ReviewQuery {

    public static final String MOVIES_TABLE = "movie_star_db.movies";
    public static final String USERS_TABLE = "movie_star_db.users";
    public static final String REVIEWS_TABLE = "movie_star_db.reviews";
    public static final String JOIN = " JOIN ";
    public static final String ON = " ON ";

    public static final String FIND_ALL_REVIEW =
            "SELECT " + REVIEWS_TABLE + ".review_id, " + REVIEWS_TABLE + ".text, " + USERS_TABLE + ".login, "
                    + MOVIES_TABLE + ".title, " + REVIEWS_TABLE + ".status, " + REVIEWS_TABLE + ".user_id, "
                    + REVIEWS_TABLE + ".movie_id " + "FROM " + REVIEWS_TABLE + JOIN
                    + USERS_TABLE + ON + USERS_TABLE + ".user_id = " + REVIEWS_TABLE + ".user_id " + JOIN
                    + MOVIES_TABLE + ON + MOVIES_TABLE + ".movie_id = " + REVIEWS_TABLE + ".movie_id";

    public static final String UPDATE_REVIEW = "UPDATE " + REVIEWS_TABLE + " SET text = ?, status = ?" +
            " WHERE (review_id = ?);";

    public static final String INSERT_REVIEW = "INSERT INTO " + REVIEWS_TABLE + " (user_id, movie_id, text, status)" +
            " VALUES (?, ?, ?, ?);";

    //!TODO join Likes count
    public static final String FIND_BY_ID  = "SELECT review_id, user_id, movie_id, text, status FROM " + REVIEWS_TABLE +
            " WHERE review_id = ?;";

    private ReviewQuery() {}

}
