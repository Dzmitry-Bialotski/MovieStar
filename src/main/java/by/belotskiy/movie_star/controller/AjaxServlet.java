package by.belotskiy.movie_star.controller;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxServlet", urlPatterns = {"/ajax"})
public class AjaxServlet extends HttpServlet {

    private static final String SEND_RATING_ACTION = "send_rating";
    private static final String SEND_LIKE_ACTION = "send_like";
    private static final String SEND_DISLIKE_ACTION = "send_dislike";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(RequestParameterName.ACTION);
        switch (action) {
            case SEND_RATING_ACTION:

                break;
            case SEND_LIKE_ACTION:

                break;
            case SEND_DISLIKE_ACTION:

                break;
        }
    }
}
