package br.com.dwallet.validator;

import br.com.dwallet.exception.WalletAccountAlreadyExistsException;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.model.WalletAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class WalletAccountValidatorTest {

    @Mock
    private WalletAccountRepository walletAccountRepository;

    @InjectMocks
    private WalletAccountValidator walletAccountValidator;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_throws_exception_when_walletAccount_exists_by_document() {
        WalletAccount walletAccount = WalletAccount.builder().accountNumber(123L).build();
        when(walletAccountRepository.findByAccountNumber(walletAccount.getAccountNumber())).thenReturn(walletAccount);
        assertThrows(WalletAccountAlreadyExistsException.class, () -> walletAccountValidator.validateWalletAccountExists(walletAccount));
    }

    @Test
    public void should_not_throws_exception_when_walletAccount_not_exists_by_document() {
        WalletAccount walletAccount = WalletAccount.builder().accountNumber(123L).build();
        when(walletAccountRepository.findByAccountNumber(walletAccount.getAccountNumber())).thenReturn(null);
        assertDoesNotThrow(() -> walletAccountValidator.validateWalletAccountExists(walletAccount));
    }

}