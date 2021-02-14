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

    MOVIES(new MoviesCommand()),

    MOVIE(new MovieCommand()),

    ADMIN(new AdminCommand()),

    ADMIN_MOVIES(new AdminMoviesCommand()),

    ADMIN_REVIEWS(new AdminReviewsCommand()),

    ADMIN_USERS(new AdminUsersCommand()),

    RATING(new RatingCommand()),

    REVIEW_ADD(new ReviewAddCommand()),

    REVIEW_DELETE(new ReviewDeleteCommand())
    ;

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }

}
