package br.com.qi.movies.Model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import br.com.qi.movies.Model.Review;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id // Indica que este campo é a chave primária
    private String IMDB;

    private String title;
    private String description;
    private short releaseYear;
    private String director;
    private short duration;
    private String genre;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;{
    }

    public Movie(String imdb, String title, String description, short releaseYear, String director, short duration, String genre, Object o) {
    }
}