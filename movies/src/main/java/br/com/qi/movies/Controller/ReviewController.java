package br.com.qi.movies.Controller;

import br.com.qi.movies.dto.ReviewDTO;
import br.com.qi.movies.Service.ReviewService;
import br.com.qi.movies.Service.UserService;
import br.com.qi.movies.Service.MovieService;
import br.com.qi.movies.dto.ReviewRegisterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService, UserService userService, MovieService movieService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ReviewRegisterDTO reviewRegisterDTO) {
        ReviewDTO reviewDTO = reviewService.registerOrUpdate(reviewRegisterDTO, "post");

        if (reviewDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reviewDTO, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody ReviewRegisterDTO reviewRegisterDTO) {
        ReviewDTO reviewDTO = reviewService.registerOrUpdate(reviewRegisterDTO, "put");

        if (reviewDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reviewDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable UUID id) {
        Boolean removed = reviewService.remove(id);

        if (removed) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<?> findByAuthor(@PathVariable String author) {
        List<ReviewDTO> reviewDTOList = reviewService.findByAuthor(author);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }
}