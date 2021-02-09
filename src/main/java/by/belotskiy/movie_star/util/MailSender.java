package by.belotskiy.movie_star.util;

import javax.mail.MessagingException;

public interface MailSender {

    boolean sendMail(String content, String subject, String email) throws MessagingException;

}
