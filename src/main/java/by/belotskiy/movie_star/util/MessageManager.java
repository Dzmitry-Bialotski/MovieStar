package by.belotskiy.movie_star.util;

import java.util.ResourceBundle;

/**
 * provides language properties to email messages
 *
 * @author Dmitriy Belotskiy
 */
public class MessageManager {

    private static final String BUNDLE_NAME = "language";

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

    private MessageManager() { }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
