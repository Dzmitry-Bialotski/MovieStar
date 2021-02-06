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
            }
            else{
                commandResult =  new CommandResult(CommandResult.DEFAULT_PATH);
            }
            String urlPath = commandResult.providePathOrDefault();
            switch (commandResult.getType()){
                case FORWARD:
                    request.getRequestDispatcher(urlPath).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(request.getContextPath() + urlPath);
                    break;
                case RETURN_URL:
                    HttpSession session = request.getSession();
                    String returnUrl = (String)session.getAttribute(SessionAttributeName.RETURN_URL);
                    response.sendRedirect(returnUrl);
                    break;
            }
        } catch (CommandException e) {
            throw new ServletException(e);
        }
    }
}
