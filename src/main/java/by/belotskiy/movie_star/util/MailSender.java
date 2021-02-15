package by.belotskiy.movie_star.util;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

public interface MailSender {

    boolean sendMail(String content, String subject, String email) throws MessagingException;
    boolean sendVerificationEmail(String email, User user) throws MessagingException, ServiceException;
    String calculateMdHash(String str) throws NoSuchAlgorithmException;
}
