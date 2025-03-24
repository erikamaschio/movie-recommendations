package br.com.qi.movies.Repository;

import br.com.qi.movies.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByCpf(String cpf);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    @Query("SELECT u.email FROM User u")
    List<String> findAllEmails();
}