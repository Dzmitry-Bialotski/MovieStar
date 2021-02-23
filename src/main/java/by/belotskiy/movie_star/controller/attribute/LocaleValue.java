package by.belotskiy.movie_star.controller.attribute;

/**
 * Contains a set of constants for locale values
 *
 * @author Dmitriy Belotskiy
 */
public enum LocaleValue {
    RU("ru"),
    EN("en");

    private final String locale;

    LocaleValue(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
