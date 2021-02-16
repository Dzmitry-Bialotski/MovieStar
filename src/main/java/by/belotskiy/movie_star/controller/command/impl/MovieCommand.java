package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.ReviewService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieCommand implements ActionCommand {
    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();
    private final ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return new CommandResult(UrlPath.MOVIE, CommandResult.Type.FORWARD);
    }
}
