package by.belotskiy.movie_star.model.validator;

import org.apache.commons.validator.EmailValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    private static final String PASSWORD_REGEX = "[\\w~!-]{5,20}";

    private UserValidator() {
    }

    public static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.matches();
        String log = result ? "Password is valid" : "Password is not valid";
        LOGGER.log(Level.DEBUG, log);
        return result;
    }

    public static boolean isEmailValid(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        boolean result = validator.isValid(email);
        String log = result ? "Email is valid" : "Email is not valid";
        LOGGER.log(Level.DEBUG, log);
        return result;
    }
    public static boolean isLoginValid(String login){
        return false;
    }
}
