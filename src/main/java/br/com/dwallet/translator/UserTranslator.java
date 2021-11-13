package br.com.dwallet.translator;

import br.com.dwallet.model.User;
import br.com.dwallet.model.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserTranslator {

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .document(user.getDocument())
                .password(user.getPassword())
                .lastUpdateAt(user.getLastUpdateAt())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User fromDTO(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .document(userDTO.getDocument())
                .password(userDTO.getPassword())
                .lastUpdateAt(userDTO.getLastUpdateAt())
                .createdAt(userDTO.getCreatedAt())
                .build();
    }

}
