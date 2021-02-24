package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestMethod;
import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.enums.Genre;
import by.belotskiy.movie_star.model.entity.enums.MovieType;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import by.belotskiy.movie_star.model.validator.MovieValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Action command edits movie
 *
 * @author Dmitriy Belotskiy
 */
public class MovieEditCommand implements ActionCommand {

    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int movieId = Integer.parseInt(request.getParameter(RequestParameterName.MOVIE_ID));
        Optional<Movie> optionalMovie;
        try {
            optionalMovie = movieService.findMovie(movieId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if(optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();
            if(request.getMethod().equals(RequestMethod.GET)){
                request.setAttribute(RequestParameterName.MOVIE, movie);
                return new CommandResult(PagePath.ADMIN_MOVIE_EDIT, CommandResult.Type.FORWARD);
            }
            else if(request.getMethod().equals(RequestMethod.POST)){
                movie.setTitle((String)request.getAttribute(RequestParameterName.TITLE));
                movie.setCountry((String)request.getAttribute(RequestParameterName.COUNTRY));
                movie.setYear(Integer.parseInt(request.getParameter(RequestParameterName.YEAR)));
                movie.setGenre(Genre.valueOf(request.getParameter(RequestParameterName.GENRE)));
                movie.setMovieType(MovieType.valueOf(request.getParameter(RequestParameterName.MOVIE_TYPE)));
                movie.setAgeCategory(Integer.parseInt(request.getParameter(RequestParameterName.AGE_CATEGORY)));
                movie.setDescription((String)request.getAttribute(RequestParameterName.DESCRIPTION));
                movie.setYoutubeTrailer((String)request.getAttribute(RequestParameterName.YOUTUBE_TRAILER));
                movie.setImagePath((String)request.getAttribute(RequestParameterName.IMAGE_PATH));
                boolean isValid = MovieValidator.validateMovie(movie);
                if(isValid){
                    try {
                        movieService.updateMovie(movie);
                    } catch (ServiceException e) {
                        throw new CommandException(e);
                    }
                }
                return new CommandResult(UrlPath.ADMIN_MOVIES_DO, CommandResult.Type.REDIRECT);
            }
        }
        return new CommandResult(UrlPath.ADMIN_DO, CommandResult.Type.REDIRECT);
    }
}
