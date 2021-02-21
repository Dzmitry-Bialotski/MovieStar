package by.belotskiy.movie_star.util;

import by.belotskiy.movie_star.model.entity.Review;

import java.util.Comparator;

public class ReviewComparator implements Comparator<Review> {
    @Override
    public int compare(Review first, Review second) {
        return second.getId().compareTo(first.getId());
    }
}
