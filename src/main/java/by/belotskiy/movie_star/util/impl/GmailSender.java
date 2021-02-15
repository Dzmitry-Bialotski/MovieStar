package by.belotskiy.movie_star.util.impl;

import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.util.MailSender;
import by.belotskiy.movie_star.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class GmailSender implements MailSender {

    private static final Logger LOGGER = LogManager.getLogger(GmailSender.class);
    private static final String EMAIL_KEY = "mail.smtp.user";
    private static final String PASSWORD_KEY = "mail.smtp.password";
    private static final String MAIL_PROPERTY_PATH = "mail.properties";
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    private static final Properties properties = new Properties();
    private static final String MESSAGE_EMAIL_VERIFICATION_SUBJECT = "message.email.verification.subject";
    private static final String MESSAGE_EMAIL_VERIFICATION_TEXT = "message.email.verification.text";
    private static final String EMAIL_LINK = "<a href=\"http://localhost:8080/MovieStar/email_confirm.do?userId=%d&token=%s\">%s</a>";
    private static final String MESSAGE_EMAIL_VERIFICATION_PRESS = "message.email.verification.press";

    private static GmailSender instance;

    private GmailSender(){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(MAIL_PROPERTY_PATH);
            properties.load(input);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "could not load resources");
        }
    }

    public static final String SALT = "s7l3T1hTEA3";

    public static GmailSender getInstance(){
        if(instance == null){
            instance = new GmailSender();
        }
        return instance;
    }

    @Override
    public boolean sendMail(String content, String subject, String email) throws MessagingException {
        String email_sender = properties.getProperty(EMAIL_KEY);
        String password = properties.getProperty(PASSWORD_KEY);
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email_sender, password);
                    }
                });

        MimeMessage message = new MimeMessage(session);

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(subject);
        message.setContent(content, CONTENT_TYPE);
        Transport.send(message);
        return true;
    }

    public boolean sendVerificationEmail(String email, User user) throws MessagingException, ServiceException {
        String subject = MessageManager.getProperty(MESSAGE_EMAIL_VERIFICATION_SUBJECT);
        String text = MessageManager.getProperty(MESSAGE_EMAIL_VERIFICATION_TEXT);
        String press = MessageManager.getProperty(MESSAGE_EMAIL_VERIFICATION_PRESS);
        String token;
        try {
            token =  calculateMdHash(user.getPasswordHash() + SALT);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Error while calculating md hash", e);
        }
        String link = String.format(EMAIL_LINK, user.getId(), token, press);
        text = text + System.lineSeparator() + link;
        return sendMail(text, subject, email);
    }

    public String calculateMdHash(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(str.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashInBytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
