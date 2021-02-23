package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Review;
import by.belotskiy.movie_star.model.service.ReviewService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Action command provides admin page to block/unblock reviews
 *
 * @author Dmitriy Belotskiy
 */
public class AdminReviewsCommand implements ActionCommand {

    private final ReviewService reviewService = ServiceFactory.getInstance().getReviewService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<Review> reviews;
        try {
            reviews = reviewService.findReviewsForAdmin();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(RequestParameterName.REVIEWS, reviews);
        return new CommandResult(PagePath.ADMIN_REVIEWS, CommandResult.Type.FORWARD);
    }
}
