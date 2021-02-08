package by.belotskiy.movie_star.util;

public class ImageValidator {
    private static final String jpg = ".jpg";
    private static final String png = ".png";

    public static boolean validateExtension(String filename){
        boolean isJpg = filename.endsWith(jpg);
        boolean isPng = filename.endsWith(png);
        boolean isValid = isJpg || isPng;
        return isValid;
    }
}
