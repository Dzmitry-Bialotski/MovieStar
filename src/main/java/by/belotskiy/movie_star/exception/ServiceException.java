package by.belotskiy.movie_star.exception;

/**
 * An exception that provides information on errors thrown by Service objects.
 *
 * @author Dmitriy Belotskiy
 */
public class ServiceException extends Exception{

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
