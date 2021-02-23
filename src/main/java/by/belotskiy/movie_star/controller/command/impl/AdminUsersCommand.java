package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.service.UserService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Action command provides admin page to block/unblock users
 *
 * @author Dmitriy Belotskiy
 */
public class AdminUsersCommand implements ActionCommand {

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<User> users;
        try {
            users = userService.findALlUsers();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(RequestParameterName.USERS, users);
        return new CommandResult(PagePath.ADMIN_USERS, CommandResult.Type.FORWARD);
    }
}
