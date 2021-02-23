package by.belotskiy.movie_star.util;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
/**
 * Interface to send mail messages
 *
 * @author Dmitriy Belotskiy
 */
public interface MailSender {
    /**
     * Send mail with subject and content to email
     *
     * @author Dmitriy Belotskiy
     */
    boolean sendMail(String content, String subject, String email) throws MessagingException;

    /**
     * Send verification mail for user to email
     *
     * @author Dmitriy Belotskiy
     */
    boolean sendVerificationEmail(String email, User user) throws MessagingException, ServiceException;

    /**
     * Calculate token for email confirmation link
     *
     * @author Dmitriy Belotskiy
     */
    String calculateMdHash(String str) throws NoSuchAlgorithmException;
}
