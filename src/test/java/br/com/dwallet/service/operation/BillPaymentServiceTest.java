package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationType;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.service.WalletAccountService;
import br.com.dwallet.validator.operation.BillPaymentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class BillPaymentServiceTest {

    private static final BigDecimal VALUE = new BigDecimal("12.32");
    private static final String ID_USER = "aqswde";
    private static final String ID_WALLET_ACCOUNT = "q1w2e3";

    @Mock
    private BillPaymentValidator billPaymentValidator;

    @Mock
    private WalletAccountService walletAccountService;

    @Mock
    private OperationTimeLineService operationTimeLineService;

    @Mock
    private WalletAccountBalanceService walletAccountBalanceService;

    @InjectMocks
    private BillPaymentService billPaymentService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        billPaymentService.setWalletAccountService(walletAccountService);
        billPaymentService.setOperationTimeLineService(operationTimeLineService);
        billPaymentService.setWalletAccountBalanceService(walletAccountBalanceService);
    }

    @Test
    public void should_bill_payment_and_verify() {
        WalletAccount walletAccount = WalletAccount.builder().build();
        when(walletAccountService.getWalletAccountByIdAndIdUser(ID_WALLET_ACCOUNT, ID_USER)).thenReturn(walletAccount);
        when(walletAccountBalanceService.subtractAmountToWalletAccount(walletAccount, VALUE)).thenReturn(walletAccount);

        billPaymentService.payBill(VALUE, ID_USER, ID_WALLET_ACCOUNT);

        verify(billPaymentValidator).validateBillPayment(eq(VALUE));
        verify(walletAccountService).getWalletAccountByIdAndIdUser(ID_WALLET_ACCOUNT, ID_USER);
        verify(walletAccountBalanceService).subtractAmountToWalletAccount(walletAccount, VALUE);
        verify(walletAccountService).saveWalletAccount(walletAccount);
        verify(operationTimeLineService).createOperationTimeLineForOutComing(walletAccount, VALUE, OperationType.BILL_PAYMENT.name());
    }

}