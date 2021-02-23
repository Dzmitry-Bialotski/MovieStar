package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.enums.Status;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Action command blocks movie
 *
 * @author Dmitriy Belotskiy
 */
public class MovieBlockCommand implements ActionCommand {

    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int movieId = Integer.parseInt(request.getParameter(RequestParameterName.MOVIE_ID));
        Optional<Movie> optionalMovie;
        try{
            optionalMovie = movieService.findMovie(movieId);
        }catch (ServiceException e) {
            throw new CommandException("could n`t find movie with such id", e);
        }
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setStatus(Status.BLOCKED);
            try{
                movieService.updateMovie(movie);
            }catch (ServiceException e) {
                throw new CommandException("could n`t update movie", e);
            }
        }
        return new CommandResult(UrlPath.ADMIN_MOVIES_DO, CommandResult.Type.REDIRECT);
    }
}
