package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import by.belotskiy.movie_star.model.service.impl.MovieServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieEditCommand implements ActionCommand {
    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int movieId = (int) request.getAttribute(RequestParameterName.MOVIE_ID);
        //movieService.editMovie(movieId);

        return new CommandResult(UrlPath.ADMIN_MOVIES, CommandResult.Type.FORWARD);
    }
}
