package by.belotskiy.movie_star.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter sets "UTF-8" encoding
 *
 * @author Dmitriy Belotskiy
 */
public class EncodingFilter implements Filter {

    private static final String EncodingParameterName = "encoding";
    private String code;

    public void init(FilterConfig fConfig) {
        code = fConfig.getInitParameter(EncodingParameterName);
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (codeRequest == null || !codeRequest.equalsIgnoreCase(code)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    public void destroy() { }
}
