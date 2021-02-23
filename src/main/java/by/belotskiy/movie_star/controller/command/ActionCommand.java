package by.belotskiy.movie_star.controller.command;

import by.belotskiy.movie_star.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface that represents "Command" pattern. Used by a controller.
 *
 * @author Dmitriy Belotskiy
 */
public interface ActionCommand {
    /**
     * Processes a request from controller and returns the page to be redirected.
     *
     * @param request request object from page.
     * @return CommandResult
     * @throws CommandException if an exception has occurred while executing.
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
