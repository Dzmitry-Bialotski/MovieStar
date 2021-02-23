package by.belotskiy.movie_star.controller.tag;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.model.entity.User;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag checks if user has provided permissions
 *
 * @author Dmitriy Belotskiy
 */
public class AccessUserTag extends TagSupport {
    private int userId;

    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        User user = (User)session.getAttribute(SessionAttributeName.USER);
        if(user != null){
            if(user.getId() == userId){
                return EVAL_BODY_INCLUDE;
            }
        }
        return SKIP_BODY;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
