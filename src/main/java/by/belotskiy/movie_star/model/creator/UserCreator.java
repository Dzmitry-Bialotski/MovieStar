package by.belotskiy.movie_star.model.creator;

import by.belotskiy.movie_star.model.entity.Role;
import by.belotskiy.movie_star.model.entity.User;

public class UserCreator {
    public static User createUser(String login, String passwordHash){
        User user = new User(login, null, passwordHash,
                Role.SPECTATOR, null, false);
        return user;
    }

    public static User createUser(int id, String login, String email, String passwordHash,
                                  Role role, String avatar_path, boolean emailConfirmed){
        User user = new User(login, null, passwordHash,
                Role.SPECTATOR, null, false);
        user.setId(id);
        return user;
    }
}
