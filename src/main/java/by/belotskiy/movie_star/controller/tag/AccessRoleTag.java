package by.belotskiy.movie_star.controller.tag;

import by.belotskiy.movie_star.controller.attribute.SessionAttributeName;
import by.belotskiy.movie_star.model.entity.User;
import by.belotskiy.movie_star.model.entity.enums.Role;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Tag checks if userRole has provided permissions
 *
 * @author Dmitriy Belotskiy
 */
public class AccessRoleTag extends TagSupport {

    private String accessRole;

    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        User user = (User)session.getAttribute(SessionAttributeName.USER);
        if(user != null){
            Role userRole = user.getRole();
            if(Role.valueOf(accessRole).getPriority() <= userRole.getPriority()){
                return EVAL_BODY_INCLUDE;
            }
        }
        return SKIP_BODY;
    }

    public void setAccessRole(String accessRole) {
        this.accessRole = accessRole;
    }
}
