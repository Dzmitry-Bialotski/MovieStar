package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.util.MailGenerator;
import by.belotskiy.movie_star.util.MailSender;
import by.belotskiy.movie_star.util.impl.GmailSender;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailSendCommand implements ActionCommand {

    private final MailSender mailSender = GmailSender.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String email = request.getParameter(RequestParameterName.EMAIL);
        String content = MailGenerator.generateContent();
        String subject = MailGenerator.generateSubject();
        boolean result;
        try {
            result = mailSender.sendMail(content, subject, email);
        } catch (MessagingException e) {
            throw new CommandException(e);
        }

        return new CommandResult(UrlPath.PROFILE, CommandResult.Type.REDIRECT);
    }
}
