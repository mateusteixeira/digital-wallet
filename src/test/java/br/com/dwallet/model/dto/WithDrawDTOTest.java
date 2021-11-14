package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WithDrawDTOTest {

    private static final String PATH_TO_FILE = "/withDraw.json";
    private static final BigDecimal AMOUNT = new BigDecimal("12.45");

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        WithDrawDTO withDrawDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, WithDrawDTO.class, WithDrawDTOTest.class);
        assertNotNull(withDrawDTO);
        assertEquals(AMOUNT, withDrawDTO.getAmount());
    }
}