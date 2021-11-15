package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OperationErrorDTOTest {

    private static final String PATH_TO_FILE = "/operationError.json";
    private static final String ACCOUNT_NAME = "conta1";
    private static final String ERROR = "someError";
    private static final String TYPE = "TED";
    private static final LocalDateTime OPERATION_AT = LocalDateTime.of(2021, 11, 13, 0, 25, 46);

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        OperationErrorDTO operationErrorDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, OperationErrorDTO.class, OperationErrorDTOTest.class);
        assertNotNull(operationErrorDTO);
        assertEquals(ACCOUNT_NAME, operationErrorDTO.getAccountName());
        assertEquals(ERROR, operationErrorDTO.getError());
        assertEquals(TYPE, operationErrorDTO.getType());
        assertEquals(OPERATION_AT, operationErrorDTO.getOperationAt());
    }
}