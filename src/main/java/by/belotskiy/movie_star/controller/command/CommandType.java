package by.belotskiy.movie_star.controller.command;

import by.belotskiy.movie_star.controller.command.impl.*;

public enum CommandType {

    HOME(new HomeCommand()),

    LOGIN(new LoginCommand()),

    LOGOUT(new LogoutCommand()),

    REGISTER(new RegisterCommand()),

    CHANGE_LOCALE_COMMAND(new ChangeLocaleCommand()),

    PROFILE(new ProfileCommand()),

    PROFILE_EDIT(new ProfileEditCommand()),

    EMAIL_SEND(new EmailSendCommand()),

    EMAIL_CONFIRM(new EmailConfirmCommand()),

    USER_BLOCK(new UserBlockCommand()),

    MOVIE_DELETE(new MovieDeleteCommand()),

    MOVIE_ADD(new MovieAddCommand()),

    MOVIE_EDIT(new MovieEditCommand()),
    ;

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }

}
