package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameterName.CURRENT_LOCALE);
        HttpSession session = request.getSession();
        if(locale != null && !locale.isEmpty()){
            session.setAttribute(SessionAttributeName.CURRENT_LOCALE, locale);
        }
        String returnUrl = request.getParameter(RequestParameterName.RETURN_URL);
        if(returnUrl != null && !returnUrl.isEmpty()){
            return new CommandResult(returnUrl, CommandResult.Type.RETURN_URL);
        }
        return new CommandResult(UrlPath.HOME, CommandResult.Type.REDIRECT);
    }
}
