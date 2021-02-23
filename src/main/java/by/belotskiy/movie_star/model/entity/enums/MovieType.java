package by.belotskiy.movie_star.model.entity.enums;

/**
 * Type of movie.
 *
 * @author Dmitriy Belotskiy
 */
public enum MovieType {
    FILM("film"),
    SERIES("series"),
    ANIME("anime"),
    CARTOON("cartoon");

    private String name;
    MovieType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
