package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestAttributeName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.locale.Locale;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String locale = (String)request.getAttribute(RequestAttributeName.CURRENT_LOCALE);
        HttpSession session = request.getSession();
        if(locale != null && !locale.isEmpty()){
            session.setAttribute(SessionAttributeName.CURRENT_LOCALE, locale);
        }
        /*TODO return url from session
         String return_url = (String)session.getAttribute(SessionAttributeName.RETURN_URL);
         return new CommandResult(return_url, CommandResult.Type.REDIRECT);*/
        return new CommandResult(UrlPath.HOME, CommandResult.Type.REDIRECT);
    }
}
