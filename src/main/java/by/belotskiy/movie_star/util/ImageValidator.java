package by.belotskiy.movie_star.util;

/**
 * Checks file extension if it is image
 *
 * @author Dmitriy Belotskiy
 */
public class ImageValidator {
    private static final String jpg = ".jpg";
    private static final String png = ".png";
    private static final String jpeg = ".jpeg";
    private static final String ico = ".ico";
    private static final String svg = ".svg";
    private static final String gif = ".gif";
    private static final String jif = ".jif";
    private static final String jfif = ".jfif";
    private static final String tiff = ".tiff";

    public static boolean validateExtension(String filename){
        boolean isValid = false;
        if(filename.endsWith(jpg)){
            isValid = true;
        }else if(filename.endsWith(png)){
            isValid = true;
        }else if(filename.endsWith(jpeg)){
            isValid = true;
        }else if(filename.endsWith(ico)){
            isValid = true;
        }else if(filename.endsWith(svg)){
            isValid = true;
        }else if(filename.endsWith(gif)){
            isValid = true;
        }else if(filename.endsWith(jif)){
            isValid = true;
        }else if(filename.endsWith(jfif)){
            isValid = true;
        }else if(filename.endsWith(tiff)){
            isValid = true;
        }

        return isValid;
    }
}
