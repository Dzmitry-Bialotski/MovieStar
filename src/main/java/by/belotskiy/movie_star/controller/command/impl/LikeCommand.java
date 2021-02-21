package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestMethod;
import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LikeCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if(request.getMethod().equals(RequestMethod.POST)){
            int reviewId = Integer.parseInt(request.getParameter(RequestParameterName.REVIEW_ID));
            int test = 10;
        }else if(request.getMethod().equals(RequestMethod.GET)){
            return new CommandResult(UrlPath.HOME_DO, CommandResult.Type.RETURN_URL);
        }
        return null;
    }
}
