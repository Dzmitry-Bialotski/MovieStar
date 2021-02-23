package by.belotskiy.movie_star.util.comparator;

import by.belotskiy.movie_star.model.entity.Review;

import java.util.Comparator;

/**
 * Comparator to sort reviews starting with the latest one
 *
 * @author Dmitriy Belotskiy
 */
public class ReviewComparator implements Comparator<Review> {
    @Override
    public int compare(Review first, Review second) {
        return second.getId().compareTo(first.getId());
    }
}
