package by.belotskiy.movie_star.exception;

/**
 * An exception that provides information on errors thrown by Dao objects.
 *
 * @author Dmitriy Belotskiy
 */
public class DaoException extends Exception{

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
