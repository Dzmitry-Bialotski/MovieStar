package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.service.UserService;
import by.belotskiy.movie_star.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileEditCommand implements ActionCommand {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String first_name = request.getParameter(RequestParameterName.FIRST_NAME);
        String second_name = request.getParameter(RequestParameterName.SECOND_NAME);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttributeName.USER);
        user.setFirstName(first_name);
        user.setSecondName(second_name);
        boolean isSuccessful;
        try {
            isSuccessful = userService.updateUser(user);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if(isSuccessful){
            return new CommandResult(UrlPath.PROFILE, CommandResult.Type.REDIRECT);
        }
        session.setAttribute(SessionAttributeName.ERROR_MESSAGE, "User changes were not saved");
        return new CommandResult(UrlPath.PROFILE_EDIT, CommandResult.Type.REDIRECT);
    }
}
