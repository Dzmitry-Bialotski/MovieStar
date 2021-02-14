package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.service.MovieService;
import by.belotskiy.movie_star.service.impl.MovieServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieDeleteCommand implements ActionCommand {

    private MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int movieId = (int) request.getAttribute(RequestParameterName.MOVIE_ID);
        //movieService.deleteMovie(movieId);

        return new CommandResult(UrlPath.ADMIN_MOVIES, CommandResult.Type.FORWARD);
    }
}
