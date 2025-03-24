package br.com.qi.movies.Service;

import br.com.qi.movies.Model.Movie;
import br.com.qi.movies.Model.Review;
import br.com.qi.movies.Repository.MovieRepository;
import br.com.qi.movies.dto.MovieDTO;
import br.com.qi.movies.dto.MovieRegisterDTO;
import br.com.qi.movies.dto.ReviewDTO;
import br.com.qi.movies.dto.ReviewRegisterDTO;
import br.com.qi.movies.validators.FieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieDTO registerOrUpdate(MovieRegisterDTO movieregisterDTO) {
        if(!DTOIsValid(movieregisterDTO)) {
            return null;
        }

        Movie movie = movieregisterDTO.toModel();
        return MovieDTO.toDTO(movieRepository.save(movie));
    }

    public List<MovieDTO> findAll() {
        List<Movie> movies = movieRepository.findAll();

        return convertModelListToDTOList(movies);
    }

    public MovieDTO findById(String imdb) {
        Optional<Movie> movie = movieRepository.findById(imdb);

        if (movie.isEmpty()) {
            return null;
        }

        return MovieDTO.toDTO(movie.get());
    }

    public Boolean remove(String imdb) {
        if(!movieRepository.existsById(imdb)) {
            return false;
        }

        movieRepository.deleteById(imdb);

        return true;
    }

    public List<MovieDTO> findByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitleContaining(title);

        return convertModelListToDTOList(movies);
    }

    public List<MovieDTO> findByReleaseYear(short year) {
        List<Movie> movies = movieRepository.findByReleaseYear(year);

        return convertModelListToDTOList(movies);
    }

    public List<MovieDTO> findByDirector(String name) {
        List<Movie> movies = movieRepository.findByDirectorContaining(name);

        return convertModelListToDTOList(movies);
    }

    public List<MovieDTO> findByGenre(String genre) {
        List<Movie> movies = movieRepository.findByGenreContaining(genre);

        return convertModelListToDTOList(movies);
    }

    private Boolean DTOIsValid(MovieRegisterDTO movieRegisterDTO) {
        if (!FieldsValidator.isValidIMDB(movieRegisterDTO.getIMDB())) {
            return false;
        } else if (!FieldsValidator.isValidTitle(movieRegisterDTO.getTitle())) {
            return false;
        } else if (!FieldsValidator.isValidDescription(movieRegisterDTO.getDescription())) {
            return false;
        } else if (!FieldsValidator.isValidReleaseYear(movieRegisterDTO.getReleaseYear())) {
            return false;
        } else if (!FieldsValidator.isValidDirector(movieRegisterDTO.getDirector())) {
            return false;
        } else if (!FieldsValidator.isValidDuration(movieRegisterDTO.getDuration())) {
            return false;
        } else return FieldsValidator.isValidGenre(movieRegisterDTO.getGenre());
    }

    private List<MovieDTO> convertModelListToDTOList(List<Movie> movieList) {
        List<MovieDTO> movieDTOList = new ArrayList<>();

        for (Movie movie : movieList) {
            movieDTOList.add(MovieDTO.toDTO(movie));
        }

        return movieDTOList;
    }
}