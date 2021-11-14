package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ErrorHandleDTOTest {

    private static final String PATH_TO_FILE = "/errorHandle.json";
    private static final String MESSAGE = "An unexpected error occurs";

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        ErrorHandleDTO errorHandleDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, ErrorHandleDTO.class, ErrorHandleDTOTest.class);
        assertNotNull(errorHandleDTO);
        assertEquals(MESSAGE, errorHandleDTO.getMessage());
    }

}