package by.belotskiy.movie_star.controller;

import by.belotskiy.movie_star.controller.attribute.RequestParameterName;
import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.controller.path.UrlPath;
import by.belotskiy.movie_star.exception.ServiceException;
import by.belotskiy.movie_star.model.entity.Like;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.service.LikeService;
import by.belotskiy.movie_star.model.service.factory.ServiceFactory;
import org.apache.commons.lang3.tuple.MutablePair;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Controller used for ajax actions
 *
 * @author Dmitriy Belotskiy
 */
@WebServlet(urlPatterns = UrlPath.AJAX, name = "AjaxServlet")
public class AjaxServlet extends HttpServlet {

    private static final String AJAX_SUBSTRING = ".ajax";

    private static final String SLASH = "/";

    private static final String LIKE = "like";
    private static final String DISLIKE = "dislike";
    private static final String LIKES = "likes";
    private static final String DISLIKES = "dislikes";

    LikeService likeService = ServiceFactory.getInstance().getLikeService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String commandName = parseCommandName(url);
        if(commandName == null){
            return;
        }
        response.setContentType("application/json");
        int reviewId = Integer.parseInt(request.getParameter(RequestParameterName.REVIEW_ID));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributeName.USER);
        int userId = user.getId();
        try {
            Optional<Like> optionalLike = likeService.findLike(userId, reviewId);
            Like like;
            if(optionalLike.isPresent()){
                like = optionalLike.get();
                if(commandName.equals(LIKE)){
                    if(like.isLike()){
                        likeService.deleteLike(userId,reviewId);
                    }else{
                        like.setLike(true);
                        likeService.update(like);
                    }
                }
                else if(commandName.equals(DISLIKE)){
                    if(!like.isLike()){
                        likeService.deleteLike(userId,reviewId);
                    }else{
                        like.setLike(false);
                        likeService.update(like);
                    }
                }
            }else{
                like = new Like(userId, reviewId, commandName.equals(LIKE));
                likeService.update(like);
            }
            MutablePair<Integer, Integer> likes_dislikes = likeService.calcLikesAndDislikes(reviewId);
            String likes = likes_dislikes.left.toString();
            String dislikes = likes_dislikes.right.toString();
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add(LIKES, likes)
                    .add(DISLIKES, dislikes)
                    .build();
            response.getWriter().write(jsonObject.toString());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private static String parseCommandName(String url){
        String commandName;
        int doPosition = url.indexOf(AJAX_SUBSTRING);
        if(doPosition == -1){
            return null;
        }
        int lastSlashPosition = url.lastIndexOf(SLASH);
        commandName = url.substring(lastSlashPosition + 1, doPosition);
        return commandName;
    }
}
