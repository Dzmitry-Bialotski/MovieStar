package by.belotskiy.movie_star.controller.command;

import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ConnectionPoolException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    CommandResult execute(HttpServletRequest request) throws CommandException;
}
