package br.com.qi.movies.dto;

import br.com.qi.movies.Model.Review;
import lombok.*;
import br.com.qi.movies.Model.Movie;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private UUID id;
    private String author;
    private byte rating;
    private String comment;
    private LocalDate date;
    private String userName;
    private Movie movie;

    public static ReviewDTO toDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getAuthor(),
                review.getRating(),
                review.getComment(),
                review.getDate(),
                review.getUser().getName(), // Assumindo que User tem um campo 'name'
                review.getMovie()
        );
    }
}