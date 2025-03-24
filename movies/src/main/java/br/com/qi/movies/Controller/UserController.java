package br.com.qi.movies.Controller;

import br.com.qi.movies.Model.User;
import br.com.qi.movies.dto.MovieDTO;
import br.com.qi.movies.dto.UserDTO;
import br.com.qi.movies.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        UserDTO userDTOResponse = userService.registerOrUpdate(userDTO, "post");

        if (userDTOResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return new ResponseEntity<>(userDTOResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UserDTO userDTO) {
        UserDTO userDTOResponse = userService.registerOrUpdate(userDTO, "put");

        if (userDTOResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return new ResponseEntity<>(userDTOResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> findById(@PathVariable String cpf) {
        UserDTO userDTO = userService.findById(cpf);

        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> remove(@PathVariable String cpf) {
        Boolean removed = userService.remove(cpf);

        if (!removed) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        UserDTO userDTO = userService.findByName(name);

        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.findByEmail(email);

        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}