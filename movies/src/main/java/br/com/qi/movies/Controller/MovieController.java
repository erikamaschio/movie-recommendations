package br.com.qi.movies.Controller;

import br.com.qi.movies.dto.MovieDTO;
import br.com.qi.movies.dto.MovieRegisterDTO;
import br.com.qi.movies.Service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/register")
    public ResponseEntity<MovieDTO> register(@RequestBody MovieRegisterDTO movieRegisterDTO) {
        MovieDTO movieDTO = movieService.registerOrUpdate(movieRegisterDTO);

        if (movieDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody MovieRegisterDTO movieRegisterDTO) {
        MovieDTO movieDTO = movieService.registerOrUpdate(movieRegisterDTO);

        if (movieDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll() {
        List<MovieDTO> movies = movieService.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{imdb}")
    public ResponseEntity<?> findByIMDB(@PathVariable String imdb) {
        MovieDTO movieDTO = movieService.findById(imdb);

        if (movieDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{imdb}")
    public ResponseEntity<?> remove(@PathVariable String imdb) {
        Boolean removed = movieService.remove(imdb);

        if (!removed) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<MovieDTO>> findByTitle(@PathVariable String title) {
        List<MovieDTO> movies = movieService.findByTitle(title);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<MovieDTO>> findByReleaseYear(@PathVariable short year) {
        List<MovieDTO> movies = movieService.findByReleaseYear(year);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/director/{name}")
    public ResponseEntity<List<MovieDTO>> findByDirector(@PathVariable String name) {
        List<MovieDTO> movies = movieService.findByDirector(name);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieDTO>> findByGenre(@PathVariable String genre) {
        List<MovieDTO> movies = movieService.findByGenre(genre);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}