package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DepositDTOTest {

    private static final String PATH_TO_FILE = "/deposit.json";
    private static final BigDecimal AMOUNT = new BigDecimal("12.45");

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        DepositDTO depositDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, DepositDTO.class, DepositDTOTest.class);
        assertNotNull(depositDTO);
        assertEquals(AMOUNT, depositDTO.getAmount());
    }

}