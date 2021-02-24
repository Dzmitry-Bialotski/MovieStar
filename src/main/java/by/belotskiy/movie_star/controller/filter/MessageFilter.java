package by.belotskiy.movie_star.controller.filter;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter used to move error message to the next request if it appears in session
 *
 * @author Dmitriy Belotskiy
 */
public class MessageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String errorMessage = (String)session.getAttribute(SessionAttributeName.ERROR_MESSAGE);
        String successMessage = (String)session.getAttribute(SessionAttributeName.REGISTER_COMPLETED);
        if(errorMessage != null){
            request.setAttribute(RequestParameterName.ERROR_MESSAGE,errorMessage);
        }
        if(successMessage != null){
            request.setAttribute(RequestParameterName.SUCCESS_MESSAGE, successMessage);
        }
        session.removeAttribute(SessionAttributeName.ERROR_MESSAGE);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
