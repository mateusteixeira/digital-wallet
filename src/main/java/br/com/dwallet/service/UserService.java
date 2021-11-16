package br.com.dwallet.service;

import br.com.dwallet.exception.UserNotFoundException;
import br.com.dwallet.model.User;
import br.com.dwallet.model.UserRepository;
import br.com.dwallet.model.dto.UserDTO;
import br.com.dwallet.translator.UserTranslator;
import br.com.dwallet.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private final UserValidator userValidator;

    private final UserRepository userRepository;

    private final UserTranslator userTranslator;

    public UserService(UserValidator userValidator, UserRepository userRepository, UserTranslator userTranslator) {
        this.userValidator = userValidator;
        this.userRepository = userRepository;
        this.userTranslator = userTranslator;
    }

    public UserDTO createUser(UserDTO userDTO) {
        log.info("Creating user: {} with document {}", userDTO.getName(), userDTO.getDocument());
        User user = userTranslator.fromDTO(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        userValidator.validateUserExists(user);
        return userTranslator.toDTO(userRepository.save(user));
    }

    public UserDTO getUserById(String idUser) {
        User user = getUserOrThrowNotFoundException(idUser);
        return userTranslator.toDTO(user);
    }

    public User getUserOrThrowNotFoundException(String idUser) {
        Optional<User> userOp = userRepository.findById(idUser);
        return userOp.orElseThrow(() -> new UserNotFoundException(String.format("User for id %s not found", idUser)));
    }

    public List<UserDTO> getAllUsers(Pageable paging) {
        return userRepository.findAll(paging).stream().map(userTranslator::toDTO).collect(Collectors.toList());
    }

    public void updateUser(UserDTO userDTO, String idUser) {
        log.info("Updating user: {}", userDTO.getName());
        User user = getUserOrThrowNotFoundException(idUser);
        user.setDocument(userDTO.getDocument());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setLastUpdateAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public void deleteUser(String idUser) {
        userRepository.deleteById(idUser);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
