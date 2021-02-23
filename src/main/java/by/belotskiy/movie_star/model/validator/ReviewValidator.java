package by.belotskiy.movie_star.model.validator;

import by.belotskiy.movie_star.model.entity.Review;

/**
 * Validate review
 *
 * @author Dmitriy Belotskiy
 */
public class ReviewValidator {

    private ReviewValidator(){}

    public static boolean validateReview(Review review) {
        if (review.getText().length() > 3000) {
            return false;
        }
        return true;
    }
}