package by.belotskiy.movie_star.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Class used to operate with commands
 *
 * @author Dmitriy Belotskiy
 */
public class CommandProvider {

    private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class);

    private static final String DO_SUBSTRING = ".do";

    private static final String SLASH = "/";

    private CommandProvider() { }

    /**
     * Defines an ActionCommand from the request.
     *
     * @param request HttpServletRequest object, which may contain the ActionCommand.
     * @return empty Optional object if command is empty or not present in the request,
     * otherwise - Optional object of ActionCommand object.
     */
    public static Optional<ActionCommand> defineCommand(HttpServletRequest request) {
        Optional<ActionCommand> result;
        String url = request.getRequestURI();
        String stringCommand = parseCommandName(url);
        if (stringCommand != null && !stringCommand.isEmpty()) {
            try {
                CommandType commandType = CommandType.valueOf(stringCommand.toUpperCase());
                ActionCommand command = commandType.getCommand();
                result = Optional.of(command);
                LOGGER.log(Level.DEBUG, "Command has been defined: " + commandType);
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.WARN, "Unsupported command");
                result = Optional.empty();
            }
        } else {
            result = Optional.empty();
        }
        return result;
    }

    /**
     * Parse url and define command name between last "/" add ".do"
     *
     * @param url request url
     * @return command name
     */
    public static String parseCommandName(String url){
        String commandName;
        int doPosition = url.indexOf(DO_SUBSTRING);
        if(doPosition == -1){
            return null;
        }
        int lastSlashPosition = url.lastIndexOf(SLASH);
        commandName = url.substring(lastSlashPosition + 1, doPosition);
        return commandName;
    }

    /**
     * defines command type from request
     *
     * @param request HttpServletRequest object
     * @return command name
     */
    public static Optional<CommandType> defineCommandType(HttpServletRequest request){
        String url = request.getRequestURI();
        String stringCommand = parseCommandName(url);
        if(stringCommand == null){
            return Optional.empty();
        }
        CommandType commandType = CommandType.valueOf(stringCommand.toUpperCase());
        return Optional.of(commandType);
    }
}
