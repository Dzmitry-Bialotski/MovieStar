package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.Review;
import by.belotskiy.movie_star.model.service.LikeService;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.RatingService;
import by.belotskiy.movie_star.model.service.ReviewService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import org.apache.commons.lang3.tuple.MutablePair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

/**
 * Action command provides movie page
 *
 * @author Dmitriy Belotskiy
 */
public class MovieCommand implements ActionCommand {
    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();
    private final ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
    private final RatingService ratingService = ServiceFactory.getInstance().getRatingService();
    private final LikeService likeService = ServiceFactory.getInstance().getLikeService();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int movieId = Integer.parseInt(request.getParameter(RequestParameterName.MOVIE_ID));
        Optional<Movie> optionalMovie;
        List<Review> reviews;
        double ratingValue;
        try {
            optionalMovie = movieService.findMovie(movieId);
            reviews = reviewService.findAllReviewsByMovieId(movieId);
            ratingValue = ratingService.calcRating(movieId);
            for(Review review : reviews){
                MutablePair<Integer, Integer> likePair = likeService.calcLikesAndDislikes(review.getId());
                review.setLikes(likePair.left);
                review.setDislikes(likePair.right);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        String formattedRating = new DecimalFormat("#0.00").format(ratingValue);
        if(optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();
            movie.setReviews(reviews);
            movie.setRating(formattedRating);
            request.setAttribute(RequestParameterName.MOVIE, movie);
        }
        return new CommandResult(PagePath.MOVIE, CommandResult.Type.FORWARD);
    }
}
