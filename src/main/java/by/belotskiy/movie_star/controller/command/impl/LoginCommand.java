package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.validator.UserValidator;
import by.belotskiy.movie_star.service.UserService;
import by.belotskiy.movie_star.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    private final String INCORRECT_LOGIN_OR_PASSWORD_MESSAGE = "Incorrect login or password";
    
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);
        boolean rememberMe = Boolean.parseBoolean(request.getParameter(RequestParameterName.REMEMBER_NE));
        HttpSession session = request.getSession();

        String errorMessage = UserValidator.validateUserForLogin(login,password);
        if(errorMessage != null && !errorMessage.isEmpty()){
            session.setAttribute(SessionAttributeName.ERROR_MESSAGE, errorMessage);
            return new CommandResult(UrlPath.REGISTER, CommandResult.Type.REDIRECT);
        }

        Optional<User> optionalUser;
        try{
            optionalUser = userService.login(login,password);
        }catch(ServiceException e){
            throw new CommandException(e);
        }

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            session.setAttribute(SessionAttributeName.LOGIN, login);
            session.setAttribute(SessionAttributeName.USER_ID, user.getId());
            session.setAttribute(SessionAttributeName.ROLE, user.getRole());
            if(rememberMe){
                //save to cookies
            }
            String returnUrl = (String)session.getAttribute(SessionAttributeName.RETURN_URL);
            if(returnUrl != null && !returnUrl.isEmpty()){
                return new CommandResult(returnUrl, CommandResult.Type.RETURN_URL);
            }else{
                return new CommandResult(UrlPath.HOME, CommandResult.Type.REDIRECT);
            }
        }else {
            session.setAttribute(SessionAttributeName.ERROR_MESSAGE, INCORRECT_LOGIN_OR_PASSWORD_MESSAGE);
            return new CommandResult(UrlPath.LOGIN, CommandResult.Type.REDIRECT);
        }
    }
}
