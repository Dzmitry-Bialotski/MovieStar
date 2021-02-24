package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Rating;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.service.RatingService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Action command sends rating
 *
 * @author Dmitriy Belotskiy
 */
public class RatingCommand implements ActionCommand {

    private final RatingService ratingService = ServiceFactory.getInstance().getRatingService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String rating_string = (String)request.getAttribute(RequestParameterName.RATING);
        if(rating_string == null || rating_string.isEmpty()){
            return new CommandResult(UrlPath.HOME_DO, CommandResult.Type.RETURN_URL);
        }
        int rating_value = Integer.parseInt(rating_string);
        int movieId = Integer.parseInt(request.getParameter(RequestParameterName.MOVIE_ID));
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttributeName.USER);
        int userId = user.getId();
        Rating rating = new Rating(rating_value, userId, movieId);
        try {
            ratingService.update(rating);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new CommandResult(UrlPath.HOME_DO, CommandResult.Type.RETURN_URL);
    }
}
