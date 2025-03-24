package br.com.qi.movies.Model;

import jakarta.persistence.*;
import lombok.*;
import br.com.qi.movies.Model.Movie;
import br.com.qi.movies.Model.User;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String author;
    private byte rating;
    private String comment;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "cpf")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}