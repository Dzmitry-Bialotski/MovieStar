package by.belotskiy.movie_star.controller.command;

import by.belotskiy.movie_star.controller.command.impl.HomeCommand;
import by.belotskiy.movie_star.controller.command.impl.LoginCommand;
import by.belotskiy.movie_star.controller.command.impl.LogoutCommand;
import by.belotskiy.movie_star.controller.command.impl.RegisterCommand;

public enum CommandType {

    HOME(new HomeCommand()),

    LOGIN(new LoginCommand()),

    LOGOUT(new LogoutCommand()),

    REGISTER(new RegisterCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }

}
