package by.belotskiy.movie_star.controller.command;

import by.belotskiy.movie_star.controller.path.UrlPath;

/**
 * Contains 3 types of command result, used by FrontController
 *
 * @author Dmitriy Belotskiy
 */
public class CommandResult {

    public static final String DEFAULT_PATH = UrlPath.HOME_DO;

    private String path;
    private Type type;
    public enum Type{
        FORWARD,
        REDIRECT,
        RETURN_URL
    }

    public CommandResult(String path, Type type) {
        this.path = path;
        this.type = type;
    }

    public CommandResult(String path) {
        this.path = path;
        type = Type.FORWARD;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String providePathOrDefault() {
        boolean isNullOrEmpty = path == null || path.isEmpty();
        return isNullOrEmpty ? DEFAULT_PATH : path;
    }
}
