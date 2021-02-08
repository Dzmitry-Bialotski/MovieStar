package by.belotskiy.movie_star.controller.listener;

import by.belotskiy.movie_star.controller.attribute.LocaleValue;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(SessionAttributeName.CURRENT_LOCALE, LocaleValue.EN.getLocale());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
