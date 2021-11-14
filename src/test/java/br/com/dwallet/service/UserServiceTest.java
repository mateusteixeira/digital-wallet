package br.com.dwallet.service;

import br.com.dwallet.exception.UserNotFoundException;
import br.com.dwallet.model.User;
import br.com.dwallet.model.UserRepository;
import br.com.dwallet.model.dto.UserDTO;
import br.com.dwallet.translator.UserTranslator;
import br.com.dwallet.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private static final String ID_USER = "aqsw";

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserTranslator userTranslator;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_save_new_user() {
        UserDTO userDTO = UserDTO.builder().build();
        User user = mock(User.class);
        when(userTranslator.fromDTO(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userTranslator.toDTO(user)).thenReturn(userDTO);

        UserDTO userCreatedDTO = userService.createUser(userDTO);
        assertNotNull(userCreatedDTO);

        verify(user).setCreatedAt(any());
        verify(userValidator).validateUserExists(user);
        verify(userRepository).save(user);
    }

    @Test
    public void should_get_user_by_id() {
        User user = User.builder().build();
        UserDTO userDTO = UserDTO.builder().build();
        when(userRepository.findById(ID_USER)).thenReturn(Optional.of(user));
        when(userTranslator.toDTO(user)).thenReturn(userDTO);

        UserDTO userFindDTO = userService.getUserById(ID_USER);
        assertNotNull(userFindDTO);

        verify(userRepository).findById(ID_USER);
        verify(userTranslator).toDTO(user);
    }

    @Test
    public void should_throws_exception_when_get_user_by_id_not_found() {
        when(userRepository.findById(ID_USER)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(ID_USER));

        verify(userRepository).findById(ID_USER);
        verify(userTranslator, never()).toDTO(any());
    }

    @Test
    public void should_get_all_users() {
        User user = User.builder().build();
        UserDTO userDTO = UserDTO.builder().build();
        PageImpl<User> users = new PageImpl<>(List.of(user));
        when(userRepository.findAll(Pageable.unpaged())).thenReturn(users);
        when(userTranslator.toDTO(user)).thenReturn(userDTO);

        List<UserDTO> allUsers = userService.getAllUsers(Pageable.unpaged());
        assertNotNull(allUsers);
        assertEquals(1, allUsers.size());

        verify(userRepository).findAll(any(Pageable.class));
        verify(userTranslator).toDTO(user);
    }

    @Test
    public void should_update_user() {
        User user = mock(User.class);
        UserDTO userDTO = mock(UserDTO.class);
        when(userRepository.findById(ID_USER)).thenReturn(Optional.of(user));

        userService.updateUser(userDTO, ID_USER);
        verify(userRepository).findById(ID_USER);
        verify(userRepository).save(user);
    }

    @Test
    public void should_not_update_user_when_user_not_found() {
        when(userRepository.findById(ID_USER)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(ID_USER));

        verify(userRepository).findById(ID_USER);
        verify(userRepository, never()).save(any());
    }

    @Test
    public void should_delete_user_by_id() {
        userService.deleteUser(ID_USER);
        verify(userRepository).deleteById(ID_USER);
    }

    @Test
    public void should_delete_all_users() {
        userService.deleteAllUsers();
        verify(userRepository).deleteAll();
    }
}