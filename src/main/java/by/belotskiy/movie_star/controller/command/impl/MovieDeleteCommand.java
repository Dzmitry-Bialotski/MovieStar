package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action command deletes movie
 *
 * @author Dmitriy Belotskiy
 */
public class MovieDeleteCommand implements ActionCommand {

    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int movieId = Integer.parseInt(request.getParameter(RequestParameterName.MOVIE_ID));
        try {
            movieService.deleteMovie(movieId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new CommandResult(UrlPath.ADMIN_MOVIES_DO, CommandResult.Type.REDIRECT);
    }
}
