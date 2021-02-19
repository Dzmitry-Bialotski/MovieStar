package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import by.belotskiy.movie_star.model.service.impl.MovieServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MoviesCommand implements ActionCommand {

    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();
    private final int MOVIES_COUNT = 3;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        /*String pageString = request.getParameter(RequestParameterName.PAGE);
        int page;
        if(pageString == null || pageString.isEmpty()){
            page = 1;
        }
        else{
            page = Integer.parseInt(pageString);
        }*/
        List<Movie> movies;
        try {
            movies = movieService.findALlMovies();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(RequestParameterName.MOVIES, movies);
        return new CommandResult(PagePath.MOVIES, CommandResult.Type.FORWARD);
    }
}
