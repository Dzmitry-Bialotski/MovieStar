package by.belotskiy.movie_star.util;

import java.util.ResourceBundle;

public class MessageManager {

    private static final String BUNDLE_NAME = "language";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);;

    private MessageManager() { }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
