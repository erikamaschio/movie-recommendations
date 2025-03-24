package br.com.qi.movies.validators;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class FieldsValidator {
    private static final String CPF_REGEX = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static boolean isValidCPF(String cpf) {
        return Pattern.matches(CPF_REGEX, cpf);
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.length() < 5 || email.length() > 100) {
            return false;
        }

        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidIMDB(String imdb) {
        if (imdb == null || imdb.length() != 9) return false;

        for (int i = 0; i < imdb.length(); i++) {

            if (!Character.isDigit(imdb.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidRating(byte rating) {
        return rating >= 0 && rating <= 10;
    }

    public static boolean isValidComment(String comment) {
        return comment != null && comment.length() >= 15 && comment.length() <= 300;
    }

    public static boolean isValidTitle(String title) {
        return title != null && title.length() >= 3 && title.length() <= 150;
    }

    public static boolean isValidReleaseYear(short releaseYear) {
        return releaseYear >= 1900;
    }

    public static boolean isValidDirector(String director) {
        return director != null && director.length() >= 3 && director.length() <= 100;
    }

    public static boolean isValidDescription(String description) {
        return description != null && description.length() >= 3 && description.length() <= 300;
    }

    public static boolean isValidGenre(String genre) {
        return genre != null && genre.length() >= 3 && genre.length() <= 50;
    }

    public static boolean isValidDuration(short duration) {
        return duration >= 60;
    }

    public static boolean isValidName(String name) {
        return name != null && name.length() >= 5 && name.length() <= 100;
    }
}
