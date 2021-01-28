package by.belotskiy.movie_star.controller.command;

import by.belotskiy.movie_star.controller.attribute.RequestAttributeName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class CommandProvider {

    private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class);

    private static final String DO_SUBSTRING = ".do";

    private static final String SLASH = "/";

    private CommandProvider() { }

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
            LOGGER.log(Level.WARN, "Command is null or empty");
            result = Optional.empty();
        }
        return result;
    }

    public static String parseCommandName(String url){
        String commandName;
        int doPosition = url.indexOf(DO_SUBSTRING);
        int lastSlashPosition = url.lastIndexOf(SLASH);
        commandName = url.substring(lastSlashPosition + 1, doPosition);
        return commandName;
    }
}
