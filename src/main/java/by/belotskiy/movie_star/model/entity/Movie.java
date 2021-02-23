package by.belotskiy.movie_star.model.entity;

import by.belotskiy.movie_star.model.entity.enums.Genre;
import by.belotskiy.movie_star.model.entity.enums.MovieType;
import by.belotskiy.movie_star.model.entity.enums.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity of movie.
 *
 * @author Dmitriy Belotskiy
 */
public class Movie extends BaseEntity implements Serializable {
    private String title;
    private String country;
    private int year;
    private Genre genre;
    private MovieType movieType;
    private int ageCategory;
    private String description;
    private String youtubeTrailer;
    private String imagePath;
    private List<Review> reviews = new ArrayList<>();
    private String rating;

    public Movie(String title, String country, int year, Genre genre, MovieType movieType, int ageCategory,
                 String description, String youtubeTrailer, Status status, String imagePath) {
        this.title = title;
        this.country = country;
        this.year = year;
        this.genre = genre;
        this.movieType = movieType;
        this.ageCategory = ageCategory;
        this.description = description;
        this.youtubeTrailer = youtubeTrailer;
        this.imagePath = imagePath;
        this.setStatus(status);
    }

    public Movie(int id, String title, String country, int year, Genre genre, MovieType movieType, int ageCategory,
                 String description, String youtubeTrailer, Status status, String imagePath) {
        super(id);
        this.title = title;
        this.country = country;
        this.year = year;
        this.genre = genre;
        this.movieType = movieType;
        this.ageCategory = ageCategory;
        this.description = description;
        this.youtubeTrailer = youtubeTrailer;
        this.imagePath = imagePath;
        this.setStatus(status);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeTrailer() {
        return youtubeTrailer;
    }

    public void setYoutubeTrailer(String youtubeTrailer) {
        this.youtubeTrailer = youtubeTrailer;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return getYear() == movie.getYear() && getAgeCategory() == movie.getAgeCategory()
                && Objects.equals(getTitle(), movie.getTitle())
                && Objects.equals(getCountry(), movie.getCountry())
                && getGenre() == movie.getGenre()
                && Objects.equals(getDescription(), movie.getDescription())
                && Objects.equals(getYoutubeTrailer(), movie.getYoutubeTrailer());
    }
    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + getYear() ^ (getYear() >>> 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        result = 31 * result  + getAgeCategory() ^ (getAgeCategory() >>> 32);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getYoutubeTrailer() != null ? getYoutubeTrailer().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Movie{")
                .append("title='").append(title).append('\'')
                .append(", country='").append(country).append('\'')
                .append(", year=").append(year)
                .append(", genre=").append(genre)
                .append(", movieType=").append(movieType)
                .append(", ageCategory=").append(ageCategory)
                .append(", youtubeTrailer='").append(youtubeTrailer).append('\'')
                .append(", imagePath='").append(imagePath).append('\'')
                .append('}').toString();
    }

}
