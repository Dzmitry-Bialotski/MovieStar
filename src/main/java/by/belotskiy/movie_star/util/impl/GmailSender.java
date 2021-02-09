package by.belotskiy.movie_star.util.impl;

import by.belotskiy.movie_star.util.MailSender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GmailSender implements MailSender {

    private static final Logger LOGGER = LogManager.getLogger(GmailSender.class);
    private static final String EMAIL_KEY = "mail.smtp.user";
    private static final String PASSWORD_KEY = "mail.smtp.password";
    private static final String MAIL_PROPERTY_PATH = "mail.properties";
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    private static final Properties properties = new Properties();

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
}
