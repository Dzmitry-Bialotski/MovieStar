package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.entity.enums.Genre;
import by.belotskiy.movie_star.model.entity.enums.MovieType;
import by.belotskiy.movie_star.model.entity.enums.Status;
import by.belotskiy.movie_star.service.MovieService;
import by.belotskiy.movie_star.service.impl.MovieServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieAddCommand implements ActionCommand {
    MovieService movieService = MovieServiceImpl.getInstance();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String title = request.getParameter(RequestParameterName.TITLE);
        String country = request.getParameter(RequestParameterName.COUNTRY);
        int year = Integer.parseInt(request.getParameter(RequestParameterName.YEAR));
        Genre genre = Genre.valueOf(request.getParameter(RequestParameterName.GENRE));
        MovieType movieType = MovieType.valueOf(request.getParameter(RequestParameterName.MOVIE_TYPE));
        int ageCategory = Integer.parseInt(request.getParameter(RequestParameterName.AGE_CATEGORY));
        String shortDescription = request.getParameter(RequestParameterName.SHORT_DESCRIPTION);
        String description = request.getParameter(RequestParameterName.DESCRIPTION);
        String youtubeTrailer = request.getParameter(RequestParameterName.YOUTUBE_TRAILER);
        String imagePath = request.getParameter(RequestParameterName.IMAGE_PATH);
        Movie movie = new Movie(title, country, year, genre, movieType, ageCategory, shortDescription,
                description, youtubeTrailer, Status.ACTIVE, imagePath);
        //movieService.addMovie(movie);
        return new CommandResult(UrlPath.ADMIN_MOVIES, CommandResult.Type.REDIRECT);
    }
}
