package by.belotskiy.movie_star.controller.filter;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandProvider;
import by.belotskiy.movie_star.controller.command.CommandType;
import by.belotskiy.movie_star.model.entity.enums.Role;
import by.belotskiy.movie_star.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Filter checks user role and defines if user has such permissions
 *
 * @author Dmitriy Belotskiy
 */
public class PermissionFilter implements Filter {

    private static final Map<Role, List<CommandType>> permissions = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        List<CommandType> guestCommands = List.of(
                //command types for guest
                CommandType.LOGIN,
                CommandType.REGISTER,
                CommandType.HOME,
                CommandType.CHANGE_LOCALE_COMMAND,
                CommandType.MOVIES,
                CommandType.MOVIE,
                CommandType.PROVIDE_IMAGE
        );

        List<CommandType> spectatorCommands = new ArrayList<>(guestCommands);
        spectatorCommands.addAll(List.of(
                //command types for spectator
                CommandType.PROFILE,
                CommandType.LOGOUT,
                CommandType.PROFILE_EDIT,
                CommandType.EMAIL_SEND,
                CommandType.EMAIL_CONFIRM,
                CommandType.RATING,
                CommandType.LIKE,
                CommandType.DISLIKE
        ));
        List<CommandType> reviewerCommands = new ArrayList<>(spectatorCommands);
        reviewerCommands.addAll(List.of(
                //command types for reviewer
                CommandType.REVIEW_ADD,
                CommandType.REVIEW_DELETE
        ));
        List<CommandType> adminCommands = new ArrayList<>(reviewerCommands);
        adminCommands.addAll(List.of(
                //command types for admin
                CommandType.MOVIE_DELETE,
                CommandType.MOVIE_ADD,
                CommandType.MOVIE_EDIT,
                CommandType.USER_BLOCK,
                CommandType.USER_UNBLOCK,
                CommandType.MOVIE_BLOCK,
                CommandType.MOVIE_UNBLOCK,
                CommandType.REVIEW_BLOCK,
                CommandType.REVIEW_UNBLOCK,
                CommandType.ADMIN,
                CommandType.ADMIN_MOVIES,
                CommandType.ADMIN_REVIEWS,
                CommandType.ADMIN_USERS

        ));
        permissions.put(Role.GUEST, guestCommands);
        permissions.put(Role.SPECTATOR, spectatorCommands);
        permissions.put(Role.REVIEWER, reviewerCommands);
        permissions.put(Role.ADMIN, adminCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttributeName.USER);
        Role userRole;
        if(user == null){
            userRole = Role.GUEST;
        }else{
            userRole = user.getRole();
        }
        Optional<ActionCommand> optionalCommand = CommandProvider.defineCommand(request);
        if(optionalCommand.isEmpty()){
            filterChain.doFilter(request, response);
            return;
        }
        List<CommandType> commands = permissions.get(userRole);
        Optional<CommandType> optionalCommandType = CommandProvider.defineCommandType(request);
        if(optionalCommandType.isPresent()){
            CommandType commandType = optionalCommandType.get();
            if (commands == null || !commands.contains(commandType)) {
                response.sendError(403);
                return;
            }
        }else{
            response.sendError(403);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
