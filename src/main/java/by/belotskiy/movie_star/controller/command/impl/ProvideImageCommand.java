package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.util.FileService;
import by.belotskiy.movie_star.util.impl.FileServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Action command provides image
 *
 * @author Dmitriy Belotskiy
 */
public class ProvideImageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CAUSE_HEADER = "cause";
    private final FileService fileService = FileServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String fileName = (String)request.getAttribute(RequestParameterName.FILE_NAME);
        if (!fileName.isEmpty()) {
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                outputStream.write(fileService.readFile(fileName));
            } catch (IOException | ServiceException e) {
                LOGGER.log(Level.ERROR, e);
                response.addHeader(CAUSE_HEADER, e.getMessage());
            }
        }
        return null;
    }
}
