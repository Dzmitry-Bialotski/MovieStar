package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestMethod;
import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import by.belotskiy.movie_star.model.validator.UserValidator;
import by.belotskiy.movie_star.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Action command provides register user
 *
 * @author Dmitriy Belotskiy
 */
public class RegisterCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String LOGIN_IS_TAKEN = "User with this login is already registered";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if(request.getMethod().equals(RequestMethod.GET)){
            return new CommandResult(PagePath.REGISTER, CommandResult.Type.FORWARD);
        }
        String login = (String) request.getAttribute(RequestParameterName.LOGIN);
        String password = (String) request.getAttribute(RequestParameterName.PASSWORD);
        String passwordConfirm = (String) request.getAttribute(RequestParameterName.PASSWORD_CONFIRM);
        HttpSession session = request.getSession();

        String errorMessage = UserValidator.validateUserForRegister(login,password, passwordConfirm);
        if(errorMessage != null && !errorMessage.isEmpty()){
            session.setAttribute(SessionAttributeName.ERROR_MESSAGE, errorMessage);
            return new CommandResult(UrlPath.REGISTER_DO, CommandResult.Type.REDIRECT);
        }

        boolean isRegisterComplete;
        try {
            isRegisterComplete = userService.register(login, password, passwordConfirm);
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        if(isRegisterComplete){
            LOGGER.log(Level.INFO, "User with login " + login + "registered");
            session.setAttribute(SessionAttributeName.REGISTER_COMPLETED, "User registered successfully!");
            return new CommandResult(UrlPath.LOGIN_DO, CommandResult.Type.REDIRECT);
        }else{
            session.setAttribute(SessionAttributeName.ERROR_MESSAGE, LOGIN_IS_TAKEN);
            return new CommandResult(UrlPath.REGISTER_DO, CommandResult.Type.REDIRECT);
        }
    }
}
