package by.belotskiy.movie_star.controller;

import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandProvider;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                commandResult = command.execute(request);
            }
            else{
                commandResult =  new CommandResult(CommandResult.DEFAULT_PATH);
            }
            String urlPath = commandResult.providePathOrDefault();
            if(commandResult.getType() == CommandResult.Type.FORWARD){
                request.getRequestDispatcher(urlPath).forward(request, response);
            }
            else if(commandResult.getType() == CommandResult.Type.REDIRECT){
                response.sendRedirect(request.getContextPath() + urlPath);
            }
        } catch (CommandException e) {
            throw new ServletException(e);
        }
    }
}
