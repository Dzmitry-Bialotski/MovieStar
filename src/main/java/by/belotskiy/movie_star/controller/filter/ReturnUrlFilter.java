package by.belotskiy.movie_star.controller.filter;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter checks user role and defines if user has such permissions
 *
 * @author Dmitriy Belotskiy
 * @deprecated (“it is not useful as request header contains previous url”)
 */
public class ReturnUrlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String returnUrl = request.getParameter(RequestParameterName.RETURN_URL);
        if(returnUrl != null && !returnUrl.isEmpty()){
            session.setAttribute(SessionAttributeName.RETURN_URL, returnUrl);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
