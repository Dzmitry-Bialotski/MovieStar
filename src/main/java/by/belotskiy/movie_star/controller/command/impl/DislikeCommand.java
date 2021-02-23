package by.belotskiy.movie_star.controller.command.impl;

import by.belotskiy.movie_star.controller.attribute.RequestMethod;
import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.command.ActionCommand;
import by.belotskiy.movie_star.controller.command.CommandResult;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.CommandException;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Like;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.service.LikeService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Action command sends dislike
 *
 * @author Dmitriy Belotskiy
 * @deprecated (“replaced with ajaxServlet”)
 */
public class DislikeCommand implements ActionCommand {
    LikeService likeService = ServiceFactory.getInstance().getLikeService();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        if(request.getMethod().equals(RequestMethod.POST)){
            int reviewId = Integer.parseInt(request.getParameter(RequestParameterName.REVIEW_ID));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(SessionAttributeName.USER);
            int userId = user.getId();
            try {
                Optional<Like> optionalLike = likeService.findLike(userId, reviewId);
                Like like;
                if(optionalLike.isPresent()){
                    like = optionalLike.get();
                    if(!like.isLike()){
                        likeService.deleteLike(userId,reviewId);
                    }else{
                        like.setLike(false);
                        likeService.update(like);
                    }
                }else{
                    like = new Like(userId, reviewId, false);
                    likeService.update(like);
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return new CommandResult(UrlPath.HOME_DO, CommandResult.Type.RETURN_URL);
    }
}
