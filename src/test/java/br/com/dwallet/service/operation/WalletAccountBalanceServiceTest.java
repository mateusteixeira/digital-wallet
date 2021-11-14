package br.com.dwallet.service.operation;

import br.com.dwallet.model.WalletAccount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WalletAccountBalanceServiceTest {

    private static final BigDecimal AMOUNT = BigDecimal.ONE;

    private final WalletAccountBalanceService walletAccountBalanceService = new WalletAccountBalanceService();

    @Test
    public void should_add_to_account() {
        WalletAccount walletAccount = WalletAccount.builder().balance(BigDecimal.TEN).build();
        WalletAccount walletAccountUpdated = walletAccountBalanceService.addAmountToWalletAccount(walletAccount, AMOUNT);
        assertNotNull(walletAccountUpdated);
        assertEquals(BigDecimal.TEN.add(BigDecimal.ONE), walletAccountUpdated.getBalance());
    }

    @Test
    public void should_subtract_to_account() {
        WalletAccount walletAccount = WalletAccount.builder().balance(BigDecimal.TEN).build();
        WalletAccount walletAccountUpdated = walletAccountBalanceService.subtractAmountToWalletAccount(walletAccount, AMOUNT);
        assertNotNull(walletAccountUpdated);
        assertEquals(BigDecimal.TEN.subtract(BigDecimal.ONE), walletAccountUpdated.getBalance());
    }
}