package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationType;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.service.WalletAccountService;
import br.com.dwallet.validator.operation.DepositValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DepositServiceTest {
    private static final BigDecimal VALUE = new BigDecimal("12.32");
    private static final String ID_USER = "aqswde";
    private static final String ID_WALLET_ACCOUNT = "q1w2e3";

    @Mock
    private DepositValidator depositValidator;

    @Mock
    private WalletAccountService walletAccountService;

    @Mock
    private OperationTimeLineService operationTimeLineService;

    @Mock
    private WalletAccountBalanceService walletAccountBalanceService;

    @InjectMocks
    private DepositService depositService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        depositService.setWalletAccountService(walletAccountService);
        depositService.setOperationTimeLineService(operationTimeLineService);
        depositService.setWalletAccountBalanceService(walletAccountBalanceService);
    }

    @Test
    public void should_deposit_and_verify() {
        WalletAccount walletAccount = WalletAccount.builder().build();
        when(walletAccountService.getWalletAccountByIdAndIdUser(ID_WALLET_ACCOUNT, ID_USER)).thenReturn(walletAccount);
        when(walletAccountBalanceService.addAmountToWalletAccount(walletAccount, VALUE)).thenReturn(walletAccount);

        depositService.doDeposit(VALUE, ID_USER, ID_WALLET_ACCOUNT);

        verify(depositValidator).validateDeposit(eq(VALUE));
        verify(walletAccountService).getWalletAccountByIdAndIdUser(ID_WALLET_ACCOUNT, ID_USER);
        verify(walletAccountBalanceService).addAmountToWalletAccount(walletAccount, VALUE);
        verify(walletAccountService).saveWalletAccount(walletAccount);
        verify(operationTimeLineService).createOperationTimeLineForIncoming(walletAccount, VALUE, OperationType.DEPOSIT.name());
    }

}