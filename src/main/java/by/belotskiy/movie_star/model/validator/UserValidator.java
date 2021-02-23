package by.belotskiy.movie_star.model.validator;

import org.apache.commons.validator.EmailValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validate user
 *
 * @author Dmitriy Belotskiy
 */
public class UserValidator {
    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    private static final String PASSWORD_REGEX = "[\\w~!-]{5,20}";

    private static final String LOGIN_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";


    private static final String VALID_LOGIN_MESSAGE = "Login is valid";
    private static final String VALID_PASSWORD_MESSAGE = "Password is valid";
    private static final String VALID_EMAIL_MESSAGE = "Email is valid";
    private static final String INVALID_LOGIN_MESSAGE = "Login is not valid";
    private static final String INVALID_PASSWORD_MESSAGE = "Password is not valid";
    private static final String INVALID_EMAIL_MESSAGE = "Email is not valid";
    private static final String PASSWORD_DO_NOT_MATCH_MESSAGE = "Password do not match";

    private UserValidator() {
    }

    public static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.matches();
        String log = result ? VALID_PASSWORD_MESSAGE : INVALID_PASSWORD_MESSAGE;
        LOGGER.log(Level.DEBUG, log);
        return result;
    }

    public static boolean isEmailValid(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        boolean result = validator.isValid(email);
        String log = result ? VALID_EMAIL_MESSAGE : INVALID_EMAIL_MESSAGE;
        LOGGER.log(Level.DEBUG, log);
        return result;
    }

    public static boolean isLoginValid(String login){
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        Matcher matcher = pattern.matcher(login);
        boolean result = matcher.matches();
        String log = result ? VALID_LOGIN_MESSAGE : INVALID_LOGIN_MESSAGE;
        LOGGER.log(Level.DEBUG, log);
        return result;
    }

    public static boolean isPasswordMatches(String password1, String password2){
        return password1.equals(password2);
    }

    public static String validateUserForRegister(String login, String password, String passwordConfirm){
        String errorMessage = "";
        if(!UserValidator.isLoginValid(login)){
            errorMessage = INVALID_LOGIN_MESSAGE;
        }
        else if(!UserValidator.isPasswordValid(password)){
            errorMessage = INVALID_PASSWORD_MESSAGE;
        }
        else if(!UserValidator.isPasswordMatches(password, passwordConfirm)){
            errorMessage = PASSWORD_DO_NOT_MATCH_MESSAGE;
        }
        return errorMessage;
    }

    public static String validateUserForLogin(String login, String password){
        String errorMessage = "";
        if(!UserValidator.isLoginValid(login)){
            errorMessage = INVALID_LOGIN_MESSAGE;
        }
        else if(!UserValidator.isPasswordValid(password)){
            errorMessage = INVALID_PASSWORD_MESSAGE;
        }
        return errorMessage;
    }
}
