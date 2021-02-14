package by.belotskiy.movie_star.model.entity.enums;

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
