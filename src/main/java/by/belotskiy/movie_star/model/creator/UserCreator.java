package by.belotskiy.movie_star.model.creator;

import by.belotskiy.movie_star.model.entity.Role;
import by.belotskiy.movie_star.model.entity.Status;
import by.belotskiy.movie_star.model.entity.User;

public class UserCreator {
    private UserCreator(){}

    public static User createUserAfterRegistration(String login, String passwordHash){
        String userHash = ((Integer)login.hashCode()).toString();
        return new User(login, "",passwordHash, Role.SPECTATOR, "",
                false, "", "",userHash,Status.ACTIVE);
    }
}
