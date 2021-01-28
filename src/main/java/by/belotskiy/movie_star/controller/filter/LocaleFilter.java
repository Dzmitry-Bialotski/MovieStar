package by.belotskiy.movie_star.controller.filter;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.locale.Locale;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String currentLocale = (String)session.getAttribute(SessionAttributeName.CURRENT_LOCALE);
        if(currentLocale == null || currentLocale.isEmpty()){
            session.setAttribute(SessionAttributeName.CURRENT_LOCALE, Locale.DEFAULT_LOCALE);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}