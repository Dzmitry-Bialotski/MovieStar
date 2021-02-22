package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestMethod;
import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Movie;
import by.belotskiy.movie_star.model.service.MovieService;
import by.belotskiy.movie_star.model.service.RatingService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import by.belotskiy.movie_star.util.SearchCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCommand implements ActionCommand {

    MovieService movieService = ServiceFactory.getInstance().getMovieService();
    RatingService ratingService = ServiceFactory.getInstance().getRatingService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if(request.getMethod().equals(RequestMethod.GET)){
            return new CommandResult(PagePath.SEARCH, CommandResult.Type.FORWARD);
        }else{
            String genre = request.getParameter(RequestParameterName.GENRE);
            String movieType = request.getParameter(RequestParameterName.MOVIE_TYPE);
            String ageCategory = request.getParameter(RequestParameterName.AGE_CATEGORY);
            String search = request.getParameter(RequestParameterName.SEARCH);
            Map<SearchCriteria, String> searchMap = new HashMap<>();
            if(genre != null && !genre.isEmpty()){
                searchMap.put(SearchCriteria.GENRE, genre);
            }
            if(movieType != null && !movieType.isEmpty()){
                searchMap.put(SearchCriteria.MOVIE_TYPE, movieType);
            }
            if(ageCategory != null && !ageCategory.isEmpty()){
                searchMap.put(SearchCriteria.AGE_CATEGORY, ageCategory);
            }
            if(search != null && !search.isEmpty()){
                searchMap.put(SearchCriteria.SEARCH, search);
            }
            List<Movie> movies;
            try {
                movies = movieService.searchMovies(searchMap);
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
}
