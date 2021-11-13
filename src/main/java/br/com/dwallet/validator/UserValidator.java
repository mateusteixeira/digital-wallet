package br.com.dwallet.validator;

import br.com.dwallet.exception.UserAlreadyExistsException;
import br.com.dwallet.model.User;
import br.com.dwallet.model.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUserExists(User user) {
        User existentUser = userRepository.findByDocument(user.getDocument());
        if (Objects.nonNull(existentUser)) {
            throw new UserAlreadyExistsException(String.format("User already exists for documento %s", user.getDocument()));
        }
    }

}
