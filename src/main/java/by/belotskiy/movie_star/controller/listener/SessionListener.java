package by.belotskiy.movie_star.controller.listener;

import by.belotskiy.movie_star.controller.attribute.LocaleValue;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.util.ImagePathProvider;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Initialize session with starting attributes
 *
 * @author Dmitriy Belotskiy
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(SessionAttributeName.CURRENT_LOCALE, LocaleValue.EN.getLocale());
        session.setAttribute(SessionAttributeName.DEFAULT_AVATAR_PATH, ImagePathProvider.getDefaultAvatar());
        session.setAttribute(SessionAttributeName.RETURN_URL, UrlPath.HOME_DO);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
