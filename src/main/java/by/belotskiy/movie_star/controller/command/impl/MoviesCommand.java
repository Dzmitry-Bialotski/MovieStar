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

public class MoviesCommand implements ActionCommand {

    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();
    private final int MOVIES_COUNT = 3;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String pageString = request.getParameter(RequestParameterName.PAGE);
        int page;
        if(pageString == null || pageString.isEmpty()){
            page = 1;
        }
        else{
            page = Integer.parseInt(pageString);
        }
//        List<Movie> movies = movieService.findMovies(page, MOVIES_COUNT);
//        request.setAttribute(RequestParameterName.MOVIES, movies);
        return new CommandResult(UrlPath.MOVIES, CommandResult.Type.FORWARD);
    }
}
