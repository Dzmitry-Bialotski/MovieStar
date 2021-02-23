package by.belotskiy.movie_star.util;

/**
 * Provides default avatar for user
 *
 * @author Dmitriy Belotskiy
 */
public class ImagePathProvider {
    private static final String DEFAULT_AVATAR_PATH = "/img/avatar/default.png";

    public static String getDefaultAvatar(){
        return DEFAULT_AVATAR_PATH;
    }
}
