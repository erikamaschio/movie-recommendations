package br.com.qi.movies.dto;

import lombok.*;
import br.com.qi.movies.Model.Review;
import br.com.qi.movies.Model.User;
import br.com.qi.movies.Model.Movie;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRegisterDTO {
    private UUID id;
    private String author;
    private byte rating;
    private String comment;
    private LocalDate date;
    private String userCPF;
    private String movieIMDB;

    public Review toModel(User user, Movie movie) {
        return new Review(
                this.id,
                this.author,
                this.rating,
                this.comment,
                this.date,
                user,
                movie
        );
    }
}