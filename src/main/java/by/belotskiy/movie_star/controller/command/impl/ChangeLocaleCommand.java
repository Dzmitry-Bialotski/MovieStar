package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestAttributeName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String locale = (String)request.getParameter(RequestAttributeName.CURRENT_LOCALE);
        HttpSession session = request.getSession();
        if(locale != null && !locale.isEmpty()){
            session.setAttribute(SessionAttributeName.CURRENT_LOCALE, locale);
        }
        String returnUrl = request.getParameter(RequestAttributeName.RETURN_URL);
        if(returnUrl != null && !returnUrl.isEmpty()){
            return new CommandResult(returnUrl, CommandResult.Type.RETURN_URL);
        }
        return new CommandResult(UrlPath.HOME, CommandResult.Type.REDIRECT);
    }
}
