package by.belotskiy.movie_star.controller.filter;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandProvider;
import by.belotskiy.movie_star.controller.command.CommandType;
import by.belotskiy.movie_star.controller.path.PagePath;
import by.belotskiy.movie_star.model.entity.Role;
import by.belotskiy.movie_star.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class PermissionFilter implements Filter {

    private static final Map<Role, List<CommandType>> permissions = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        List<CommandType> guestCommands = List.of(
                //command types for guest
                CommandType.LOGIN,
                CommandType.REGISTER,
                CommandType.HOME,
                CommandType.CHANGE_LOCALE_COMMAND
        );

        List<CommandType> spectatorCommands = new ArrayList<>(guestCommands);
        spectatorCommands.addAll(List.of(
                //command types for spectator
                CommandType.PROFILE,
                CommandType.LOGOUT
        ));
        List<CommandType> reviewerCommands = new ArrayList<>(spectatorCommands);
        spectatorCommands.addAll(List.of(
                //command types for reviewer
        ));
        List<CommandType> adminCommands = new ArrayList<>(reviewerCommands);
        spectatorCommands.addAll(List.of(
                //command types for admin
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
        if(!optionalCommand.isPresent()){
            filterChain.doFilter(request, response);
            return;
        }
        List<CommandType> commands = permissions.get(userRole);
        CommandType commandType = CommandProvider.defineCommandType(request).get();
        if (commands == null || !commands.contains(commandType)) {
            response.sendRedirect(request.getContextPath() + PagePath.ERROR_403);
            //response.setStatus(403);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}