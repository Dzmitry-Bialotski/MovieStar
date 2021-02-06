package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.service.UserService;
import by.belotskiy.movie_star.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ProfileCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute(SessionAttributeName.USER);
//        int id = (int)session.getAttribute(SessionAttributeName.USER_ID);
//        Optional<User> optionalUser;
//        try{
//            optionalUser = userService.findUser(id);
//            if(optionalUser.isPresent()){
//                User user = optionalUser.get();
//                session.setAttribute(SessionAttributeName.USER, user);
//            }
//        }catch (ServiceException e){
//            throw new CommandException(e);
//        }
        return new CommandResult(UrlPath.PROFILE, CommandResult.Type.FORWARD);
    }
}
