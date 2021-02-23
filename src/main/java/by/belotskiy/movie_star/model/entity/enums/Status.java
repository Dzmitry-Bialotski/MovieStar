package by.belotskiy.movie_star.model.entity.enums;

/**
 * Status od entity
 *
 * @author Dmitriy Belotskiy
 */
public enum Status {

    ACTIVE(0),

    BLOCKED(1);

    private final int statusId;

    Status(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }
}
