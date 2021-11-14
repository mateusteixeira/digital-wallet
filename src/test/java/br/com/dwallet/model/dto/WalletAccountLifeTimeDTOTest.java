package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WalletAccountLifeTimeDTOTest {

    private static final String PATH_TO_FILE = "/walletAccountLifeTime.json";
    private static final String PATH_TO_FILE_WITH_TWO_ITEMS = "/walletAccountLifeTimeWithTwoOperation.json";
    private static final int QUANTITY = 1;
    private static final String ACCOUNT_NAME = "Conta 1";
    private static final BigDecimal BALANCE = new BigDecimal("12.3");
    private static final LocalDateTime OPERATION_AT = LocalDateTime.of(2021, 11, 13, 0, 25, 46);
    private static final BigDecimal VALUE = new BigDecimal("12.45");
    private static final String TYPE = "PIX";

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        WalletAccountLifeTimeDTO walletAccountLifeTimeDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, WalletAccountLifeTimeDTO.class, WalletAccountLifeTimeDTOTest.class);
        assertNotNull(walletAccountLifeTimeDTO);
        assertEquals(QUANTITY, walletAccountLifeTimeDTO.getOperationQuantity());
        assertEquals(BALANCE, walletAccountLifeTimeDTO.getBalance());
        assertEquals(ACCOUNT_NAME, walletAccountLifeTimeDTO.getAccountName());
        List<OperationLifeTimeDTO> operationWalletAccountLifeTimeDTOS = walletAccountLifeTimeDTO.getOperationLifeTimeDTOS();
        assertEquals(QUANTITY, operationWalletAccountLifeTimeDTOS.size());
        assertNull(operationWalletAccountLifeTimeDTOS.get(0).getAccountName());
        assertEquals(OPERATION_AT, operationWalletAccountLifeTimeDTOS.get(0).getOperationAt());
        assertEquals(VALUE, operationWalletAccountLifeTimeDTOS.get(0).getValue());
        assertEquals(TYPE, operationWalletAccountLifeTimeDTOS.get(0).getType());
        assertTrue(operationWalletAccountLifeTimeDTOS.get(0).isIncoming());
    }

    @Test
    public void should_read_from_json_with_two_operations() throws IOException {
        WalletAccountLifeTimeDTO walletAccountLifeTimeDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE_WITH_TWO_ITEMS, WalletAccountLifeTimeDTO.class, WalletAccountLifeTimeDTOTest.class);
        assertNotNull(walletAccountLifeTimeDTO);
        assertEquals(2, walletAccountLifeTimeDTO.getOperationQuantity());
    }

}