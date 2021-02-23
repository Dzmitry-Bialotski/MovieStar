package by.belotskiy.movie_star.model.validator;

import by.belotskiy.movie_star.model.entity.Movie;

/**
 * Validate movie
 *
 * @author Dmitriy Belotskiy
 */
public class MovieValidator {

    private MovieValidator(){}

    public static boolean validateMovie(Movie movie){
        if(movie.getTitle().length() < 2 || movie.getTitle().length() > 120){
            return false;
        }
        if(movie.getCountry().length() < 2 || movie.getCountry().length() > 45){
            return false;
        }
        if(movie.getYear() < 1895 || movie.getYear() > 3000){
            return false;
        }
        if(movie.getAgeCategory() < 0 || movie.getAgeCategory() > 21){
            return false;
        }
        if(movie.getDescription().length() > 3000){
            return false;
        }
        if(movie.getYoutubeTrailer().length() > 100){
            return false;
        }
        if (movie.getImagePath().length() > 300) {
            return false;
        }
        return true;
    }
}
