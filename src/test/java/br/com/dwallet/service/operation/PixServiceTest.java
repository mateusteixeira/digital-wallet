package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationErrorRepository;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.WalletAccountService;
import br.com.dwallet.validator.operation.PixValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PixServiceTest {


    @Mock
    private PixValidator pixValidator;

    @Mock
    private WalletAccountService walletAccountService;

    @Mock
    private OperationErrorRepository operationErrorRepository;

    @Mock
    private OperationTimeLineService operationTimeLineService;

    @Mock
    private WalletAccountBalanceService walletAccountBalanceService;

    @InjectMocks
    private PixService pixService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        pixService.setValidator(pixValidator);
        pixService.setWalletAccountService(walletAccountService);
        pixService.setOperationErrorRepository(operationErrorRepository);
        pixService.setOperationTimeLineService(operationTimeLineService);
        pixService.setWalletAccountBalanceService(walletAccountBalanceService);
    }

    @Test
    public void should_do_process_and_verify() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder()
                .idWalletAccountFrom("q1w2e3")
                .idUserFrom("aqswde")
                .amount(new BigDecimal("123.3"))
                .idUserTo("zaxscd")
                .accountNumberTo(123L)
                .build();
        WalletAccount walletAccountFrom = WalletAccount.builder().id("1").build();
        WalletAccount walletAccountTo = WalletAccount.builder().id("2").build();

        when(walletAccountService.getWalletAccountByIdAndIdUser(asyncOperationMessage.getIdWalletAccountFrom(), asyncOperationMessage.getIdUserFrom())).thenReturn(walletAccountFrom);
        when(walletAccountBalanceService.subtractAmountToWalletAccount(walletAccountFrom, asyncOperationMessage.getAmount())).thenReturn(walletAccountFrom);
        when(walletAccountService.getWalletAccountByNumber(asyncOperationMessage.getAccountNumberTo())).thenReturn(walletAccountTo);
        when(walletAccountBalanceService.addAmountToWalletAccount(walletAccountTo, asyncOperationMessage.getAmount())).thenReturn(walletAccountTo);

        pixService.doProcess(asyncOperationMessage);

        verify(pixValidator).validate(asyncOperationMessage);
        verify(walletAccountService).getWalletAccountByIdAndIdUser(asyncOperationMessage.getIdWalletAccountFrom(), asyncOperationMessage.getIdUserFrom());
        verify(walletAccountBalanceService).subtractAmountToWalletAccount(walletAccountFrom, asyncOperationMessage.getAmount());
        verify(walletAccountService).getWalletAccountByNumber(asyncOperationMessage.getAccountNumberTo());
        verify(walletAccountBalanceService).addAmountToWalletAccount(walletAccountTo, asyncOperationMessage.getAmount());
        verify(walletAccountService).saveWalletAccount(eq(walletAccountFrom));
        verify(walletAccountService).saveWalletAccount(eq(walletAccountTo));
        verify(operationTimeLineService).createOperationTimeLineForOutComing(walletAccountFrom, asyncOperationMessage.getAmount(), asyncOperationMessage.getType());
        verify(operationTimeLineService).createOperationTimeLineForIncoming(walletAccountTo, asyncOperationMessage.getAmount(), asyncOperationMessage.getType());
    }

    @Test
    public void should_do_process_error_when_some_error_and_verify() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder()
                .idWalletAccountFrom("q1w2e3")
                .idUserFrom("aqswde")
                .amount(new BigDecimal("123.3"))
                .idUserTo("zaxscd")
                .accountNumberTo(123L)
                .build();
        WalletAccount walletAccountFrom = WalletAccount.builder().id("1").build();
        WalletAccount walletAccountTo = WalletAccount.builder().id("2").build();

        doThrow(RuntimeException.class).when(pixValidator).validate(asyncOperationMessage);
        pixService.doProcess(asyncOperationMessage);

        verify(pixValidator).validate(asyncOperationMessage);
        verify(walletAccountService, never()).getWalletAccountByIdAndIdUser(asyncOperationMessage.getIdWalletAccountFrom(), asyncOperationMessage.getIdUserFrom());
        verify(walletAccountBalanceService, never()).subtractAmountToWalletAccount(walletAccountFrom, asyncOperationMessage.getAmount());
        verify(walletAccountService, never()).getWalletAccountByNumber(asyncOperationMessage.getAccountNumberTo());
        verify(walletAccountBalanceService, never()).addAmountToWalletAccount(walletAccountTo, asyncOperationMessage.getAmount());
        verify(walletAccountService, never()).saveWalletAccount(eq(walletAccountFrom));
        verify(walletAccountService, never()).saveWalletAccount(eq(walletAccountTo));
        verify(operationTimeLineService, never()).createOperationTimeLineForOutComing(walletAccountFrom, asyncOperationMessage.getAmount(), asyncOperationMessage.getType());
        verify(operationTimeLineService, never()).createOperationTimeLineForIncoming(walletAccountTo, asyncOperationMessage.getAmount(), asyncOperationMessage.getType());
    }


}