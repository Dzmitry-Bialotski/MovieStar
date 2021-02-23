package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.CookieName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Action command provides log out user
 *
 * @author Dmitriy Belotskiy
 */
public class LogoutCommand implements ActionCommand {


    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        String currentLocale = (String) session.getAttribute(SessionAttributeName.CURRENT_LOCALE);
        session.invalidate();
        session = request.getSession(true);
        session.setAttribute(SessionAttributeName.CURRENT_LOCALE, currentLocale);
        LOGGER.log(Level.INFO, "User logged out. ");
        Cookie cookie = new Cookie(CookieName.USER_HASH, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        cookie = new Cookie(CookieName.USER_LOGIN, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new CommandResult(UrlPath.LOGIN_DO, CommandResult.Type.REDIRECT);
    }
}
