package by.belotskiy.movie_star.model.creator;

import by.belotskiy.movie_star.model.entity.enums.Role;
import by.belotskiy.movie_star.model.entity.enums.Status;
import by.belotskiy.movie_star.model.entity.User;

/**
 * Create User in different conditions.
 *
 * @author Dmitriy Belotskiy
 */
public class UserCreator {
    private UserCreator(){}
    /**
     * Create User after registration (calculate hash before constructor).
     *
     * @author Dmitriy Belotskiy
     */
    public static User createUserAfterRegistration(String login, String passwordHash){
        String userHash = ((Integer)login.hashCode()).toString();
        return new User(login, "",passwordHash, Role.SPECTATOR, "",
                false, "", "",userHash,Status.ACTIVE);
    }
}
