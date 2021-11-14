package br.com.dwallet.model.dto;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WalletAccountDTOTest {

    private static final String PATH_TO_FILE = "/walletAccount.json";
    private static final String ID = "q1w2e3";
    private static final String ACCOUNT_NAME = "asdf";
    private static final Long ACCOUNT_NUMBER = 123L;
    private static final String ID_USER = "awdese";
    private static final BigDecimal BALANCE = new BigDecimal("14.45");

    @Test
    public void should_read_from_json_and_assert_values() throws IOException {
        WalletAccountDTO walletAccountDTO = DTOTestHelper.getJsonFromFile(PATH_TO_FILE, WalletAccountDTO.class, WalletAccountDTOTest.class);
        assertNotNull(walletAccountDTO);
        assertEquals(ID, walletAccountDTO.getId());
        assertEquals(ACCOUNT_NAME, walletAccountDTO.getAccountName());
        assertEquals(ACCOUNT_NUMBER, walletAccountDTO.getAccountNumber());
        assertEquals(ID_USER, walletAccountDTO.getIdUser());
        assertEquals(BALANCE, walletAccountDTO.getBalance());
    }

}