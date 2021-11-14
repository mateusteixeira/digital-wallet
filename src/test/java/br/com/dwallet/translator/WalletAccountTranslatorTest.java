package br.com.dwallet.translator;

import br.com.dwallet.model.User;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.model.dto.WalletAccountDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WalletAccountTranslatorTest {

    private final WalletAccountTranslator walletAccountTranslator = new WalletAccountTranslator();

    @Test
    public void should_translate_from_dto() {
        WalletAccountDTO walletAccountDTO = WalletAccountDTO.builder()
                .id("a1s2d3")
                .accountName("conta1")
                .accountNumber(123L)
                .balance(BigDecimal.ZERO)
                .build();

        WalletAccount walletAccount = walletAccountTranslator.fromDTO(walletAccountDTO);
        assertNotNull(walletAccountDTO);
        assertEquals(walletAccountDTO.getId(), walletAccount.getId());
        assertEquals(walletAccountDTO.getAccountName(), walletAccount.getAccountName());
        assertEquals(walletAccountDTO.getAccountNumber(), walletAccount.getAccountNumber());
        assertEquals(walletAccountDTO.getBalance(), walletAccount.getBalance());
    }

    @Test
    public void should_translate_to_dto() {
        WalletAccount walletAccount = WalletAccount.builder()
                .id("a1s2d3")
                .accountName("conta1")
                .accountNumber(123L)
                .balance(BigDecimal.ZERO)
                .user(User.builder().id("e3r4t5").build())
                .build();

        WalletAccountDTO walletAccountDTO = walletAccountTranslator.toDTO(walletAccount);
        assertNotNull(walletAccountDTO);
        assertEquals(walletAccount.getId(), walletAccountDTO.getId());
        assertEquals(walletAccount.getAccountName(), walletAccountDTO.getAccountName());
        assertEquals(walletAccount.getAccountNumber(), walletAccountDTO.getAccountNumber());
        assertEquals(walletAccount.getBalance(), walletAccountDTO.getBalance());
        assertEquals(walletAccount.getUser().getId(), walletAccountDTO.getIdUser());
    }

}