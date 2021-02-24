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
import by.belotskiy.movie_star.util.SearchCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Action command provides movies page
 *
 * @author Dmitriy Belotskiy
 */
public class MoviesCommand implements ActionCommand {

    private final MovieService movieService = ServiceFactory.getInstance().getMovieService();
    private final RatingService ratingService = ServiceFactory.getInstance().getRatingService();
    private final static int MOVIES_COUNT = 8;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String pageString = request.getParameter(RequestParameterName.PAGE);
        int page, nextPage, prevPage;
        if(pageString == null || pageString.isEmpty()){
            page = 1;
        } else{
            page = Integer.parseInt(pageString);
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, page);
        prevPage = page <= 1 ? page : page - 1;
        nextPage = page + 1;
        String genre = request.getParameter(RequestParameterName.GENRE);
        String movieType = request.getParameter(RequestParameterName.MOVIE_TYPE);
        String ageCategory = request.getParameter(RequestParameterName.AGE_CATEGORY);
        String search = (String) request.getAttribute(RequestParameterName.SEARCH);
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
            movies = movieService.searchMovies(searchMap, page, MOVIES_COUNT);
            List<Movie> nextMovies = movieService.searchMovies(searchMap, page + 1, MOVIES_COUNT);
            if(nextMovies.isEmpty()){
                nextPage = page;
            }
            ratingService.provideMoviesWithRating(movies);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(RequestParameterName.MOVIES, movies);
        request.setAttribute(RequestParameterName.SEARCH, search);
        request.setAttribute(RequestParameterName.AGE_CATEGORY, ageCategory);
        request.setAttribute(RequestParameterName.MOVIE_TYPE, movieType);
        request.setAttribute(RequestParameterName.GENRE, genre);
        StringBuffer url = request.getRequestURL();
        String parameters = request.getQueryString();
        String nextPageHref = url.toString();
        String prevPageHref = url.toString();
        if(parameters != null && !parameters.isEmpty()){
            nextPageHref = nextPageHref + "?" + parameters;
            prevPageHref = prevPageHref + "?" + parameters;
            if(!nextPageHref.contains("page")){
                nextPageHref += "&" + "page=" + nextPage;
                prevPageHref += "&" + "page=" + prevPage;
            }else{
                int index = nextPageHref.indexOf("page=");
                index += 5;
                nextPageHref = nextPageHref.substring(0, index) + nextPage
                        + nextPageHref.substring(index+1);
                prevPageHref = prevPageHref.substring(0, index) + prevPage
                        + prevPageHref.substring(index+1);
            }
        }else{
            nextPageHref = nextPageHref + "?" + "page=" + nextPage;
            prevPageHref = prevPageHref + "?" + "page=" + prevPage;
        }
        request.setAttribute(RequestParameterName.NEXT_PAGE_HREF, nextPageHref);
        request.setAttribute(RequestParameterName.PREV_PAGE_HREF, prevPageHref);
        return new CommandResult(PagePath.MOVIES, CommandResult.Type.FORWARD);
    }
}
