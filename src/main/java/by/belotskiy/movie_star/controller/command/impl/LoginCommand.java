package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.attribute.RequestAttributeName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.model.entity.Role;
import by.belotskiy.movie_star.model.entity.Status;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.service.UserService;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        /*String login = request.getParameter(RequestAttributeName.LOGIN);
        String password = request.getParameter(RequestAttributeName.PASSWORD);
        Optional<User> optionalUser;
        try {
            optionalUser = userService.login(login, password);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        HttpSession session = request.getSession();
        String page;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getStatus() != Status.BLOCKED){
                Role role = user.getRole();
                switch (role){
                    case GUEST:
                        session.setAttribute(SessionAttributeName.USER_ROLE, SessionAttributeName.GUEST_ROLE);
                        break;
                    case SPECTATOR:
                        session.setAttribute(SessionAttributeName.USER_ROLE, SessionAttributeName.SPECTATOR_ROLE);
                        break;
                    case REVIEWER:
                        session.setAttribute(SessionAttributeName.USER_ROLE, SessionAttributeName.REVIEWER_ROLE);
                        break;
                    case ADMIN:
                        session.setAttribute(SessionAttributeName.USER_ROLE, SessionAttributeName.ADMIN_ROLE);
                        break;
                    default:
                        throw new CommandException("Unsupported role");
                }
                LOGGER.log(Level.DEBUG, "Logged in as " + role.toString());
                page = UrlPath.HOME;
                session.setAttribute(SessionAttributeName.WRONG_EMAIL_PASSWORD, null);
                session.setAttribute(SessionAttributeName.USER_BLOCKED, null);
                Map<String, String> loginInfo = new HashMap<>();
                loginInfo.put(RequestAttributeName.LOGIN, login);
                int userId = user.getId();
                loginInfo.put(SessionAttributeName.USER_ID, String.valueOf(userId));
                session.setAttribute(SessionAttributeName.LOGIN_INFO, loginInfo);
            }
            else{
                session.setAttribute(SessionAttributeName.USER_BLOCKED, true);
                page = UrlPath.LOGIN;
                LOGGER.log(Level.DEBUG, "User is blocked");
            }
        }
        else {
            session.setAttribute(SessionAttributeName.WRONG_EMAIL_PASSWORD, true);
            page = UrlPath.LOGIN;
        }
        return page;*/
        HttpSession session = request.getSession();
        String login = request.getParameter(RequestAttributeName.LOGIN);
        String password = request.getParameter(RequestAttributeName.PASSWORD);
        session.setAttribute(SessionAttributeName.LOGIN, login);
        session.setAttribute(SessionAttributeName.PASSWORD, password);
        return new CommandResult(UrlPath.HOME, CommandResult.Type.REDIRECT);
    }
}
