package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.RatingService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;

public class MoviesCommand implements ActionCommand {

    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();
    private final RatingService ratingService = ServiceFactory.getInstance().getRatingService();
    private final static int MOVIES_COUNT = 4;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String pageString = request.getParameter(RequestParameterName.PAGE);
        int page;
        if(pageString == null || pageString.isEmpty()){
            page = 1;
        } else{
            page = Integer.parseInt(pageString);
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, page);
        if(page <= 1){
            session.setAttribute(SessionAttributeName.PREVIOUS_PAGE, page);
        }else{
            session.setAttribute(SessionAttributeName.PREVIOUS_PAGE, page - 1);
        }
        session.setAttribute(SessionAttributeName.NEXT_PAGE, page + 1);
        List<Movie> movies;
        try {
            movies = movieService.findALlMovies(page, MOVIES_COUNT);
            List<Movie> nextMovies = movieService.findALlMovies(page + 1, MOVIES_COUNT);
            if(nextMovies.isEmpty()){
                session.setAttribute(SessionAttributeName.NEXT_PAGE, page);
            }
            for(Movie movie : movies){
                Double rating = ratingService.calcRating(movie.getId());
                String formattedRating = new DecimalFormat("#0.00").format(rating);
                movie.setRating(formattedRating);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(RequestParameterName.MOVIES, movies);
        return new CommandResult(PagePath.MOVIES, CommandResult.Type.FORWARD);
    }
}
