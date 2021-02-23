package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Action command provides admin page to CRUD or block/unblock movies
 *
 * @author Dmitriy Belotskiy
 */
public class AdminMoviesCommand implements ActionCommand {

    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<Movie> movies;
        try {
            movies = movieService.findALlMoviesForAdmin();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(RequestParameterName.MOVIES, movies);
        return new CommandResult(PagePath.ADMIN_MOVIES, CommandResult.Type.FORWARD);
    }
}
