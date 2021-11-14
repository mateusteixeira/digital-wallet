package br.com.dwallet.validator;

import br.com.dwallet.exception.UserAlreadyExistsException;
import br.com.dwallet.model.User;
import br.com.dwallet.model.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserValidator userValidator;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_throws_exception_when_user_exists_by_document() {
        User user = User.builder().document("01212312302").build();
        when(userRepository.findByDocument(user.getDocument())).thenReturn(user);
        assertThrows(UserAlreadyExistsException.class, () -> userValidator.validateUserExists(user));
    }

    @Test
    public void should_not_throws_exception_when_user_not_exists_by_document() {
        User user = User.builder().document("01212312302").build();
        when(userRepository.findByDocument(user.getDocument())).thenReturn(null);
        assertDoesNotThrow(() -> userValidator.validateUserExists(user));
    }
}