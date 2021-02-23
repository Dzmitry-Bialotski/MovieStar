package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Review;
import by.belotskiy.movie_star.model.entity.enums.Status;
import by.belotskiy.movie_star.model.service.ReviewService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Action command blocks review
 *
 * @author Dmitriy Belotskiy
 */
public class ReviewBlockCommand implements ActionCommand {

    private final ReviewService reviewService = ServiceFactory.getInstance().getReviewService();


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int reviewId = Integer.parseInt(request.getParameter(RequestParameterName.REVIEW_ID));
        Optional<Review> optionalReview;
        try{
            optionalReview = reviewService.findReview(reviewId);
        }catch (ServiceException e) {
            throw new CommandException("could n`t find review with such id", e);
        }
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setStatus(Status.BLOCKED);
            try{
                reviewService.updateReview(review);
            }catch (ServiceException e) {
                throw new CommandException("could n`t update review", e);
            }
        }
        return new CommandResult(UrlPath.ADMIN_REVIEWS_DO, CommandResult.Type.REDIRECT);
    }
}
