package br.com.qi.movies.Service;

import br.com.qi.movies.Model.Movie;
import br.com.qi.movies.Model.User;
import br.com.qi.movies.Repository.UserRepository;
import br.com.qi.movies.dto.MovieDTO;
import br.com.qi.movies.dto.UserDTO;
import br.com.qi.movies.validators.FieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO registerOrUpdate(UserDTO userDTO, String method) {
        if(!DTOisValid(userDTO)) {
            return null;
        }

        if(method.equals("post")) {
            if(userRepository.findAllEmails().contains(userDTO.getEmail())) {
                return null;
            }
        }

        userRepository.save(UserDTO.toModel(userDTO));

        return userDTO;
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return convertModelListToDTOList(users);
    }

    public UserDTO findById(String cpf) {
        Optional<User> user = userRepository.findById(cpf);

        if(user.isEmpty()) {
            return null;
        }

        return UserDTO.fromModel(user.get());
    }

    public Boolean remove(String cpf) {
        if(!userRepository.existsById(cpf)) {
            return false;
        }

        userRepository.deleteById(cpf);

        return true;
    }

    public UserDTO findByName(String name) {
        Optional<User> user = userRepository.findByName(name);

        if(user.isEmpty()) {
            return null;
        }

        return UserDTO.fromModel(user.get());
    }

    public UserDTO findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()) {
            return null;
        }

        return UserDTO.fromModel(user.get());
    }

    private Boolean DTOisValid(UserDTO userDTO) {
        if (!FieldsValidator.isValidCPF(userDTO.getCpf())) {
            return false;
        } else if(!FieldsValidator.isValidEmail(userDTO.getEmail())) {
            return false;
        } else return FieldsValidator.isValidName(userDTO.getName());
    }

    private List<UserDTO> convertModelListToDTOList(List<User> userList) {
        List<UserDTO> UserDTOList = new ArrayList<>();

        for (User user : userList) {
            UserDTOList.add(UserDTO.fromModel(user));
        }

        return UserDTOList;
    }
}

