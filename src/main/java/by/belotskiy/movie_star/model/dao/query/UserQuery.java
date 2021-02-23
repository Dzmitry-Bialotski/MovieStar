package by.belotskiy.movie_star.model.dao.query;

/**
 * Provides queries to work with User entity
 *
 * @author Dmitriy Belotskiy
 */
public class UserQuery {

    public static final String USERS_TABLE = "movie_star_db.users";

    public static final String FIND_ALL_USER_QUERY =
            "SELECT user_id, login, email, password_hash, role, avatar_path, email_confirmed," +
                    " user_hash, first_name, second_name, status  FROM " + USERS_TABLE;

    public static final String INSERT_USER_QUERY = "INSERT INTO " + USERS_TABLE +
            " (login, email, password_hash, role, avatar_path, email_confirmed, " +
            "user_hash, first_name, second_name, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String DELETE_USER_QUERY = "DELETE FROM " + USERS_TABLE +
            " WHERE (user_id = ?)";

    public static final String UPDATE_USER_QUERY = "UPDATE " + USERS_TABLE +
            " SET login = ?, email = ?, password_hash = ?, role = ?, avatar_path = ?," +
            " email_confirmed = ?, user_hash = ?, first_name = ?, second_name = ?, status = ?" +
            " WHERE (user_id = ?)";

    public static final String FIND_USER_WITH_LIMITS_QUERY =
            "SELECT user_id, login, email, password_hash, role, avatar_path, email_confirmed," +
                    " user_hash, first_name, second_name, status  FROM " + USERS_TABLE +
                    " ORDER BY user_id DESC LIMIT ? OFFSET ?";

    public static final String FIND_COUNT_OF_USERS_QUERY = "SELECT count(*) FROM "+ USERS_TABLE;

    private UserQuery(){}
}
