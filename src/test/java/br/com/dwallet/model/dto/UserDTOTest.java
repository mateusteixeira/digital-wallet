package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserDTOTest {

    private static final String PATH_TO_FILE = "/user.json";
    private static final String ID = "q1w2e3";
    private static final String NAME = "joao";
    private static final String DOCUMENT = "1234567890";
    private static final String EMAIL = "joao@joao.com.br";
    private static final String PASSWORD = "aqswde";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2021, 11, 13, 0, 25, 46);
    private static final LocalDateTime LAST_UPDATE_AT = LocalDateTime.of(2021, 11, 14, 0, 25, 46);

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        UserDTO userDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, UserDTO.class, UserDTOTest.class);
        assertNotNull(userDTO);
        assertEquals(ID, userDTO.getId());
        assertEquals(NAME, userDTO.getName());
        assertEquals(DOCUMENT, userDTO.getDocument());
        assertEquals(EMAIL, userDTO.getEmail());
        assertEquals(PASSWORD, userDTO.getPassword());
        assertEquals(CREATED_AT, userDTO.getCreatedAt());
        assertEquals(LAST_UPDATE_AT, userDTO.getLastUpdateAt());
    }
}