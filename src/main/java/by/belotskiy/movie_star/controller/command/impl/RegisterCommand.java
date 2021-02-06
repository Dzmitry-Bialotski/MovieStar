package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.validator.UserValidator;
import by.belotskiy.movie_star.service.UserService;
import by.belotskiy.movie_star.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private final UserService userService = UserServiceImpl.getInstance();
    private final String LOGIN_IS_TAKEN = "User with this login is already registered";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);
        String passwordConfirm = request.getParameter(RequestParameterName.PASSWORD_CONFIRM);
        HttpSession session = request.getSession();

        String errorMessage = UserValidator.validateUserForRegister(login,password, passwordConfirm);
        if(errorMessage != null && !errorMessage.isEmpty()){
            session.setAttribute(SessionAttributeName.ERROR_MESSAGE, errorMessage);
            return new CommandResult(UrlPath.REGISTER, CommandResult.Type.REDIRECT);
        }

        boolean isRegisterComplete;
        try {
            isRegisterComplete = userService.register(login, password, passwordConfirm);
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        if(isRegisterComplete){
            LOGGER.log(Level.INFO, "User with login " + login + "registered");
            return new CommandResult(UrlPath.LOGIN, CommandResult.Type.REDIRECT);
        }else{
            session.setAttribute(SessionAttributeName.ERROR_MESSAGE, LOGIN_IS_TAKEN);
            return new CommandResult(UrlPath.REGISTER, CommandResult.Type.REDIRECT);
        }
    }
}
