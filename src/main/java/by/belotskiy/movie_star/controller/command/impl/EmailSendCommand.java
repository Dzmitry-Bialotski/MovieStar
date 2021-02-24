package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.service.UserService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import by.belotskiy.movie_star.util.MailSender;
import by.belotskiy.movie_star.util.impl.GmailSender;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Action command sends email
 *
 * @author Dmitriy Belotskiy
 */
public class EmailSendCommand implements ActionCommand {

    private final MailSender mailSender = GmailSender.getInstance();
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String email = (String)request.getAttribute(RequestParameterName.EMAIL);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttributeName.USER);
        try {
            mailSender.sendVerificationEmail(email, user);
            user.setEmail(email);
            user.setEmailConfirmed(false);
            userService.updateUser(user);
        } catch (MessagingException | ServiceException e) {
            throw new CommandException(e);
        }
        return new CommandResult(UrlPath.PROFILE_DO, CommandResult.Type.REDIRECT);
    }
}
