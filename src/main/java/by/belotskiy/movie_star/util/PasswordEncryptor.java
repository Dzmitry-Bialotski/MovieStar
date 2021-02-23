package by.belotskiy.movie_star.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Encrypts password
 *
 * @author Dmitriy Belotskiy
 */
public class PasswordEncryptor {
    private static final Logger LOGGER = LogManager.getLogger(PasswordEncryptor.class);

    private PasswordEncryptor() {

    }
    public static String encrypt(String value) {
        String salt = BCrypt.gensalt(10);
        String hashedValue = BCrypt.hashpw(value, salt);
        LOGGER.log(Level.INFO, "Value has been encrypted");
        return hashedValue;
    }

    public static boolean check(String value, String encValue) {
        boolean result = BCrypt.checkpw(value, encValue);
        LOGGER.log(Level.INFO, "Value has been checked: " + result);
        return result;
    }
}
