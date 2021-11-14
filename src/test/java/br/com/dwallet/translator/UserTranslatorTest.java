package br.com.dwallet.translator;

import br.com.dwallet.model.User;
import br.com.dwallet.model.dto.UserDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTranslatorTest {

    private final UserTranslator userTranslator = new UserTranslator();

    @Test
    public void should_translate_to_dto() {
        User user = User.builder()
                .id("a23s")
                .email("joao@joao.com")
                .name("joao")
                .document("01020302")
                .password("123")
                .lastUpdateAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        UserDTO userDTO = userTranslator.toDTO(user);
        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getDocument(), userDTO.getDocument());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getLastUpdateAt(), userDTO.getLastUpdateAt());
        assertEquals(user.getCreatedAt(), userDTO.getCreatedAt());
    }

    @Test
    public void should_translate_from_dto() {
        UserDTO userDTO = UserDTO.builder()
                .id("a23s")
                .email("joao@joao.com")
                .name("joao")
                .document("01020302")
                .password("123")
                .lastUpdateAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        User user = userTranslator.fromDTO(userDTO);
        assertNotNull(user);
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getDocument(), user.getDocument());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.getLastUpdateAt(), user.getLastUpdateAt());
        assertEquals(userDTO.getCreatedAt(), user.getCreatedAt());
    }

}