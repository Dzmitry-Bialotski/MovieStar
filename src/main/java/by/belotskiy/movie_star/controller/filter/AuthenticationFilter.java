package by.belotskiy.movie_star.controller.filter;

import by.belotskiy.movie_star.controller.attribute.CookieName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.service.UserService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Filter used to authorise users with cookies and save them in session
 *
 * @author Dmitriy Belotskiy
 */
public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationFilter.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();

        if(request.getCookies() == null || session.getAttribute(SessionAttributeName.USER) != null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        List<Cookie> cookies = List.of(request.getCookies());
        String userHash = null;
        String userLogin = null;
        for (var cookie : cookies) {
            if (cookie.getName().equals(CookieName.USER_HASH)) {
                userHash = cookie.getValue();
            }
            if (cookie.getName().equals(CookieName.USER_LOGIN)) {
                userLogin = cookie.getValue();
            }
        }
        if(userHash != null && userLogin != null){
            try{
                Optional<User> optionalUser = userService.findUserWithCookies(userLogin, userHash);
                if(optionalUser.isPresent()){
                    User user = optionalUser.get();
                    session.setAttribute(SessionAttributeName.USER, user);
                }
            }catch (ServiceException e){
                LOGGER.error("Error authorizing user via cookies", e);
                String page = request.getContextPath() + UrlPath.LOGIN_DO;
                response.sendRedirect(page);
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
