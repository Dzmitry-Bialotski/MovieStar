package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.exception.CommandException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action command provides home page
 *
 * @author Dmitriy Belotskiy
 */
public class HomeCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return new CommandResult(PagePath.HOME, CommandResult.Type.FORWARD);
    }
}
