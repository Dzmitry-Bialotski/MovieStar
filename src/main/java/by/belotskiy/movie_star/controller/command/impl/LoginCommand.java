package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.CookieName;
import by.belotskiy.movie_star.controller.attribute.RequestMethod;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.entity.enums.Status;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import by.belotskiy.movie_star.model.validator.UserValidator;
import by.belotskiy.movie_star.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Action command provides log in user
 *
 * @author Dmitriy Belotskiy
 */
public class LoginCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if(request.getMethod().equals(RequestMethod.GET)){
            return new CommandResult(PagePath.LOGIN, CommandResult.Type.FORWARD);
        }
        String login = (String)request.getAttribute(RequestParameterName.LOGIN);
        String password = (String)request.getAttribute(RequestParameterName.PASSWORD);
        String[] checkBoxValues = request.getParameterValues(RequestParameterName.REMEMBER_ME);
        List<String> checkboxes = checkBoxValues != null ? List.of(checkBoxValues) : new ArrayList<>();
        boolean rememberMe = checkboxes.contains(RequestParameterName.ON);
        HttpSession session = request.getSession();
        String errorMessage = UserValidator.validateUserForLogin(login,password);
        if(errorMessage != null && !errorMessage.isEmpty()){
            session.setAttribute(SessionAttributeName.ERROR_MESSAGE, errorMessage);
            return new CommandResult(UrlPath.REGISTER_DO, CommandResult.Type.REDIRECT);
        }
        Optional<User> optionalUser;
        try{
            optionalUser = userService.login(login,password);
        }catch(ServiceException e){
            throw new CommandException(e);
        }

        if(optionalUser.isPresent()){
            LOGGER.log(Level.INFO, "user logged in");
            User user = optionalUser.get();
            if(user.getStatus() == Status.BLOCKED){
                session.setAttribute(SessionAttributeName.ERROR_MESSAGE, "user is blocked");
                return new CommandResult(UrlPath.LOGIN_DO, CommandResult.Type.REDIRECT);
            }
            session.setAttribute(SessionAttributeName.USER, user);
            if(rememberMe){
                Cookie hashCookie = new Cookie(CookieName.USER_HASH, user.getUserHash());
                Cookie loginCookie = new Cookie(CookieName.USER_LOGIN, user.getLogin());
                hashCookie.setMaxAge(24*60*60);
                loginCookie.setMaxAge(24*60*60);
                response.addCookie(hashCookie);
                response.addCookie(loginCookie);
            }
            return new CommandResult(UrlPath.HOME_DO, CommandResult.Type.REDIRECT);
        }else {
            session.setAttribute(SessionAttributeName.ERROR_MESSAGE, "Incorrect login or password");
            return new CommandResult(PagePath.LOGIN, CommandResult.Type.FORWARD);
        }
    }
}
