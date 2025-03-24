package br.com.qi.movies.dto;

import lombok.*;
import br.com.qi.movies.Model.Review;
import br.com.qi.movies.Model.Movie;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private String IMDB;
    private String title;
    private String description;
    private short releaseYear;
    private String director;
    private short duration;
    private String genre;
    private List<Review> reviews;

    public static MovieDTO toDTO(Movie movie) {
        return new MovieDTO(
                movie.getIMDB(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseYear(),
                movie.getDirector(),
                movie.getDuration(),
                movie.getGenre(),
                movie.getReviews()
        );
    }
}