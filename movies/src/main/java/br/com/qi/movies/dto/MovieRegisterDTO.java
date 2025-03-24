package br.com.qi.movies.dto;

import lombok.*;
import br.com.qi.movies.Model.Movie;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRegisterDTO {
    private String IMDB;
    private String title;
    private String description;
    private short releaseYear;
    private String director;
    private short duration;
    private String genre;

    public Movie toModel() {
        return new Movie(
                this.IMDB,
                this.title,
                this.description,
                this.releaseYear,
                this.director,
                this.duration,
                this.genre,
                null
        );
    }
}