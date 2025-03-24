package br.com.qi.movies.Service;

import br.com.qi.movies.Model.Movie;
import br.com.qi.movies.Model.Review;
import br.com.qi.movies.Model.User;
import br.com.qi.movies.Repository.MovieRepository;
import br.com.qi.movies.Repository.ReviewRepository;
import br.com.qi.movies.Repository.UserRepository;
import br.com.qi.movies.dto.ReviewDTO;
import br.com.qi.movies.dto.ReviewRegisterDTO;
import br.com.qi.movies.validators.FieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         MovieRepository movieRepository,
                         UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public ReviewDTO registerOrUpdate(ReviewRegisterDTO reviewRegisterDTO, String method) {
        if (!checkFields(reviewRegisterDTO)) {
            return null;
        }

        Optional<Movie> movie = movieRepository.findById(reviewRegisterDTO.getMovieIMDB());
        Optional<User> user = userRepository.findByCpf(reviewRegisterDTO.getUserCPF());

        if (user.isEmpty()) {
           return null;
        }

        if (movie.isEmpty()) {
            return null;
        }

       if(method.equals("post")) {
           reviewRegisterDTO.setId(null);
       } else if (!reviewRepository.existsById(reviewRegisterDTO.getId())) {
           return null;
        }

        Review review = reviewRegisterDTO.toModel(user.get(), movie.get());
        return ReviewDTO.toDTO(reviewRepository.save(review));
    }

    public List<ReviewDTO> findByAuthor(String author) {
        List<Review> review = reviewRepository.findByAuthor(author);

        return convertModelListToDTOList(review);
    }

    public Boolean remove(UUID id) {
        if(!reviewRepository.existsById(id)) {
            return false;
        }

        reviewRepository.deleteById(id);

        return true;
    }

    private Boolean checkFields(ReviewRegisterDTO reviewRegisterDTO) {
        if (reviewRegisterDTO.getAuthor() == null || reviewRegisterDTO.getAuthor().isEmpty()) {
            return false;
        } else if (!FieldsValidator.isValidRating(reviewRegisterDTO.getRating())) {
            return false;
        } else if (!FieldsValidator.isValidComment(reviewRegisterDTO.getComment())) {
            return false;
        } else if (reviewRegisterDTO.getDate() == null) {
            return false;
        } else if (!FieldsValidator.isValidCPF(reviewRegisterDTO.getUserCPF())) {
            return false;
        } else return FieldsValidator.isValidIMDB(reviewRegisterDTO.getMovieIMDB());
    }

    private List<ReviewDTO> convertModelListToDTOList(List<Review> reviewList) {
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        for (Review review : reviewList) {
            reviewDTOList.add(ReviewDTO.toDTO(review));
        }

        return reviewDTOList;
    }
}