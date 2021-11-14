package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LifeTimeDTOTest {

    private static final String PATH_TO_FILE = "/lifeTime.json";
    private static final String PATH_TO_FILE_WITH_TWO_ITEMS = "/lifeTimeWithTwoOperation.json";
    private static final int QUANTITY = 1;
    private static final String ACCOUNT_NAME = "q1w2e3";
    private static final LocalDateTime OPERATION_AT = LocalDateTime.of(2021, 11, 13, 0, 25, 46);
    private static final BigDecimal VALUE = new BigDecimal("12.45");
    private static final String TYPE = "PIX";

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        LifeTimeDTO lifeTimeDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, LifeTimeDTO.class, LifeTimeDTOTest.class);
        assertNotNull(lifeTimeDTO);
        assertEquals(QUANTITY, lifeTimeDTO.getOperationQuantity());
        List<OperationLifeTimeDTO> operationLifeTimeDTOS = lifeTimeDTO.getOperationLifeTimeDTOS();
        assertEquals(QUANTITY, operationLifeTimeDTOS.size());
        assertEquals(ACCOUNT_NAME, operationLifeTimeDTOS.get(0).getAccountName());
        assertEquals(OPERATION_AT, operationLifeTimeDTOS.get(0).getOperationAt());
        assertEquals(VALUE, operationLifeTimeDTOS.get(0).getValue());
        assertEquals(TYPE, operationLifeTimeDTOS.get(0).getType());
        assertTrue(operationLifeTimeDTOS.get(0).isIncoming());
    }

    @Test
    public void should_read_from_json_with_two_operations() throws IOException {
        LifeTimeDTO lifeTimeDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE_WITH_TWO_ITEMS, LifeTimeDTO.class, LifeTimeDTOTest.class);
        assertNotNull(lifeTimeDTO);
        assertEquals(2, lifeTimeDTO.getOperationQuantity());
    }
}