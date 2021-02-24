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
import by.belotskiy.movie_star.model.entity.enums.Status;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import by.belotskiy.movie_star.model.validator.MovieValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action command adds movie
 *
 * @author Dmitriy Belotskiy
 */
public class MovieAddCommand implements ActionCommand {
    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if(request.getMethod().equals(RequestMethod.GET)){
            return new CommandResult(PagePath.ADMIN_MOVIE_ADD, CommandResult.Type.FORWARD);
        }
        String title = (String) request.getAttribute(RequestParameterName.TITLE);
        String country =  (String)request.getAttribute(RequestParameterName.COUNTRY);
        int year = Integer.parseInt(request.getParameter(RequestParameterName.YEAR));
        Genre genre = Genre.valueOf(request.getParameter(RequestParameterName.GENRE));
        MovieType movieType = MovieType.valueOf(request.getParameter(RequestParameterName.MOVIE_TYPE));
        int ageCategory = Integer.parseInt(request.getParameter(RequestParameterName.AGE_CATEGORY));
        String description =  (String)request.getAttribute(RequestParameterName.DESCRIPTION);
        String youtubeTrailer =  (String)request.getAttribute(RequestParameterName.YOUTUBE_TRAILER);
        String imagePath =  (String)request.getAttribute(RequestParameterName.IMAGE_PATH);
        Movie movie = new Movie(title, country, year, genre, movieType, ageCategory,
                description, youtubeTrailer, Status.ACTIVE, imagePath);

        boolean isValid = MovieValidator.validateMovie(movie);
        if(isValid){
            try {
                movieService.addMovie(movie);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            return new CommandResult(UrlPath.ADMIN_MOVIES_DO, CommandResult.Type.REDIRECT);
        }
        return new CommandResult(UrlPath.ADMIN_MOVIES_DO, CommandResult.Type.REDIRECT);
    }
}
