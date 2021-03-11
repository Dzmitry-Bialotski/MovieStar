package by.belotskiy.movie_star.controller;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandProvider;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

/**
 * Main Controller class processes all requests from users.
 *
 * @author Dmitriy Belotskiy
 */
@WebServlet(urlPatterns = UrlPath.CONTROLLER, name = "controller")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<ActionCommand> optionalCommand = CommandProvider.defineCommand(request);
        try {
            CommandResult commandResult;
            if(optionalCommand.isPresent()){
                ActionCommand command = optionalCommand.get();
                commandResult = command.execute(request,response);
                if(commandResult == null){
                    return;
                }
            }
            else{
                commandResult =  new CommandResult(CommandResult.DEFAULT_PATH);
            }
            String urlPath = commandResult.providePathOrDefault();
            HttpSession session = request.getSession();
            String redirectUrl = makeFullUrlString(request);

            switch (commandResult.getType()) {
                case FORWARD -> {
                    session.setAttribute(SessionAttributeName.RETURN_URL, redirectUrl);
                    request.getRequestDispatcher(urlPath).forward(request, response);
                }
                case REDIRECT -> {
                    session.setAttribute(SessionAttributeName.RETURN_URL, redirectUrl);
                    response.sendRedirect(request.getContextPath() + urlPath);
                }
                case RETURN_URL -> {
                    //String returnUrl = (String) session.getAttribute(SessionAttributeName.RETURN_URL);
                    session.setAttribute(SessionAttributeName.RETURN_URL, request.getContextPath() + urlPath);
                    response.sendRedirect(request.getHeader("referer")); //that also works arr!! :(
                    //response.sendRedirect(returnUrl);
                }
            }
        } catch (CommandException e) {
            throw new ServletException(e);
        }
    }

    private String makeFullUrlString(HttpServletRequest request){
        StringBuffer url = request.getRequestURL();
        String parameters = request.getQueryString();
        if(parameters != null && !parameters.isEmpty()){
            url.append("?").append(parameters);
        }
        return url.toString();
    }
}
