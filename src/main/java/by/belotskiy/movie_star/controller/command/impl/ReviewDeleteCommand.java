package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.service.ReviewService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action command deletes review
 *
 * @author Dmitriy Belotskiy
 */
public class ReviewDeleteCommand implements ActionCommand {
    private final ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int reviewId = Integer.parseInt(request.getParameter(RequestParameterName.REVIEW_ID));
        try {
            reviewService.deleteReview(reviewId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new CommandResult(UrlPath.HOME_DO, CommandResult.Type.RETURN_URL);
    }
}
