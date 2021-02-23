package by.belotskiy.movie_star.model.dao.query;

/**
 * Provides queries to work with Movie entity
 *
 * @author Dmitriy Belotskiy
 */
public class MovieQuery {

    private MovieQuery() {}

    public static final String MOVIES_TABLE = "movie_star_db.movies";

    public static final String FIND_ALL_MOVIE =
            "SELECT movie_id, title, country, year, genre, movie_type, " +
                    "age_category, description, youtubeTrailer, " +
                    "image_path, status FROM " + MOVIES_TABLE;

    //! TODO join Score
    public static final String FIND_ALL_MOVIE_WITH_LIMITS =
            "SELECT movie_id, title, country, year, genre, movie_type, " +
                    "age_category, description, youtubeTrailer, " +
                    "image_path, status FROM " + MOVIES_TABLE +
                    " ORDER BY movie_id DESC LIMIT ? OFFSET ?";

    //! TODO join Reviews
    public static final String FIND_MOVIE_WITH_REVIEWS_BY_ID =
            "SELECT movie_id, title, country, year, genre, movie_type, " +
                    "age_category, description, youtubeTrailer, " +
                    "image_path, status FROM " + MOVIES_TABLE +
                    " WHERE ";

    //! TODO join Reviews
    public static final String FIND_MOVIE_BY_ID =
            "SELECT movie_id, title, country, year, genre, movie_type, " +
                    "age_category, description, youtubeTrailer, " +
                    "image_path, status FROM " + MOVIES_TABLE +
                    " WHERE movie_id = ?;";

    public static final String INSERT_MOVIE = "INSERT INTO " + MOVIES_TABLE +
            " (title, country, year, genre, movie_type, " +
            "age_category, description, youtubeTrailer,image_path, status) VALUES " +
            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_MOVIE = "UPDATE " + MOVIES_TABLE +
            " SET movie_id = ?, title = ?, country = ?, year = ?, genre = ?, movie_type = ?," +
            " age_category = ?, description = ?, youtubeTrailer = ?," +
            " image_path = ?, status = ? WHERE (movie_id = ?)";

    public static final String DELETE_MOVIE = "DELETE FROM " + MOVIES_TABLE +
            " WHERE (movie_id = ?)";

}
