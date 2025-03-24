package br.com.qi.movies.Repository;

import br.com.qi.movies.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findByTitleContaining(String title);
    List<Movie> findByReleaseYear(short year);
    List<Movie> findByDirectorContaining(String director);
    List<Movie> findByGenreContaining(String genre);
}