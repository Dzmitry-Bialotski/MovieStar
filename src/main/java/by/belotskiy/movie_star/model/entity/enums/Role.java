package by.belotskiy.movie_star.model.entity.enums;

/**
 * User role.
 *
 * @author Dmitriy Belotskiy
 */
public enum Role {
    GUEST(1),
    SPECTATOR(2),
    REVIEWER(3),
    ADMIN(4);

    private int priority;

    Role(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
