package by.belotskiy.movie_star.controller.command;

import by.belotskiy.movie_star.controller.command.impl.*;

public enum CommandType {

    HOME(new HomeCommand()),

    LOGIN(new LoginCommand()),

    LOGOUT(new LogoutCommand()),

    REGISTER(new RegisterCommand()),

    CHANGE_LOCALE_COMMAND(new ChangeLocaleCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }

}
