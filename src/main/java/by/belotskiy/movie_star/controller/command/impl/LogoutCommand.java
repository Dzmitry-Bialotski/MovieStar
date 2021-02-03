package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {


    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentLocale = (String) session.getAttribute(SessionAttributeName.CURRENT_LOCALE);
        session.invalidate();
        session.setAttribute(SessionAttributeName.CURRENT_LOCALE, currentLocale);
        LOGGER.log(Level.INFO, "User logged out. ");

        String returnUrl = request.getParameter(RequestParameterName.RETURN_URL);
        if(returnUrl != null && !returnUrl.isEmpty()){
            return new CommandResult(returnUrl, CommandResult.Type.RETURN_URL);
        }
        return new CommandResult(UrlPath.HOME, CommandResult.Type.REDIRECT);
    }
}
