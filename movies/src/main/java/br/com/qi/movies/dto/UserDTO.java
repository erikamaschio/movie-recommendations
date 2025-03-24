package br.com.qi.movies.dto;

import lombok.*;
import br.com.qi.movies.Model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String cpf;
    private String name;
    private String email;

    public static UserDTO fromModel(User user) {
        return new UserDTO(
                user.getCpf(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User toModel(UserDTO userDTO) {
        return new User(
                userDTO.getCpf(),
                userDTO.getName(),
                userDTO.getEmail(),
                null
        );
    }
}