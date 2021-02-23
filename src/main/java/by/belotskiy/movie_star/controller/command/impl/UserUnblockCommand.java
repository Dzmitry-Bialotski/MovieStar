package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.entity.enums.Status;
import by.belotskiy.movie_star.model.service.UserService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Action command unblocks user
 *
 * @author Dmitriy Belotskiy
 */
public class UserUnblockCommand implements ActionCommand {

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int userId = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));
        Optional<User> optionalUser;
        try {
            optionalUser = userService.findUser(userId);
        } catch (ServiceException e) {
            throw new CommandException("could n`t find user with such userId",e);
        }
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setStatus(Status.ACTIVE);
            try {
                userService.updateUser(user);
            } catch (ServiceException e) {
                throw new CommandException("could n`t update user",e);
            }
        }
        return new CommandResult(UrlPath.ADMIN_USERS_DO, CommandResult.Type.REDIRECT);
    }
}
