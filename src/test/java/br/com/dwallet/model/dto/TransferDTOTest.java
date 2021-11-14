package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransferDTOTest {

    private static final String PATH_TO_FILE = "/transfer.json";
    private static final String ID_USER_TO = "q1w2e3";
    private static final Long ACCOUNT_NUMBER_TO = 123L;
    private static final BigDecimal VALUE = new BigDecimal("12.34");
    private static final String TYPE = "TED";

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        TransferDTO transferDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, TransferDTO.class, TransferDTOTest.class);
        assertNotNull(transferDTO);
        assertEquals(ID_USER_TO, transferDTO.getIdUserTo());
        assertEquals(ACCOUNT_NUMBER_TO, transferDTO.getAccountNumberTo());
        assertEquals(VALUE, transferDTO.getAmount());
        assertEquals(TYPE, transferDTO.getType());
    }

}