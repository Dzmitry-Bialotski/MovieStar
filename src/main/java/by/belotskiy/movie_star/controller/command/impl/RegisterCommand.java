package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ConnectionPoolException;
import by.belotskiy.movie_star.model.dao.UserDao;
import by.belotskiy.movie_star.model.dao.impl.UserDaoImpl;
import by.belotskiy.movie_star.model.entity.Role;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.util.IdGenerator;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        //TODO register logic
        UserDao userDao = new UserDaoImpl();
        User user = new User(IdGenerator.generateId(), "dimas", "test", "qwe", Role.SPECTATOR,
                null, true);
        try {
            userDao.save(user);
        }catch (Exception e){
            throw new CommandException(e);
        }
        return new CommandResult(UrlPath.LOGIN, CommandResult.Type.REDIRECT);
    }
}
