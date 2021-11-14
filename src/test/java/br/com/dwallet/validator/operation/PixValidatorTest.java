package br.com.dwallet.validator.operation;

import br.com.dwallet.exception.AmountBusinessException;
import br.com.dwallet.exception.TransferBusinessException;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.WalletAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PixValidatorTest {

    @Mock
    private WalletAccountService walletAccountService;
    @InjectMocks
    private PixValidator pixValidator;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        pixValidator.setWalletAccountService(walletAccountService);
    }

    @Test
    public void should_throw_exception_when_amount_less_than_zero() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder()
                .amount(new BigDecimal("-1"))
                .idUserTo("123")
                .idUserFrom("aqsw")
                .accountNumberTo(123L)
                .idWalletAccountFrom("aqswde")
                .build();
        WalletAccount walletAccountTo = WalletAccount.builder().id("q1w2e3").build();
        when(walletAccountService.getWalletAccountByNumber(123L)).thenReturn(walletAccountTo);
        assertThrows(AmountBusinessException.class, () ->
                pixValidator.validate(asyncOperationMessage));
        verify(walletAccountService).getWalletAccountByNumber(123L);
    }

    @Test
    public void should_throw_exception_when_amount_equal_zero() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder()
                .amount(BigDecimal.ZERO)
                .idUserTo("123")
                .idUserFrom("aqsw")
                .accountNumberTo(123L)
                .idWalletAccountFrom("aqswde")
                .build();
        WalletAccount walletAccountTo = WalletAccount.builder().id("q1w2e3").build();
        when(walletAccountService.getWalletAccountByNumber(123L)).thenReturn(walletAccountTo);
        assertThrows(AmountBusinessException.class, () ->
                pixValidator.validate(asyncOperationMessage));
        verify(walletAccountService).getWalletAccountByNumber(123L);
    }

    @Test
    public void should_throw_exception_when_amount_greater_than_zero() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder()
                .amount(BigDecimal.TEN)
                .idUserTo("123")
                .idUserFrom("aqsw")
                .accountNumberTo(123L)
                .idWalletAccountFrom("aqswde")
                .build();
        WalletAccount walletAccountTo = WalletAccount.builder().id("q1w2e3").build();
        when(walletAccountService.getWalletAccountByNumber(123L)).thenReturn(walletAccountTo);
        assertDoesNotThrow(() -> pixValidator.validate(asyncOperationMessage));
        verify(walletAccountService).getWalletAccountByNumber(123L);
    }

    @Test
    public void should_throw_exception_when_accounts_equal() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder()
                .amount(new BigDecimal("-1"))
                .idUserTo("123")
                .idUserFrom("23233")
                .accountNumberTo(123L)
                .idWalletAccountFrom("q1w2e3")
                .build();
        WalletAccount walletAccountTo = WalletAccount.builder().id("q1w2e3").build();
        when(walletAccountService.getWalletAccountByNumber(123L)).thenReturn(walletAccountTo);
        assertThrows(TransferBusinessException.class, () ->
                pixValidator.validate(asyncOperationMessage));
        verify(walletAccountService).getWalletAccountByNumber(123L);
    }

    @Test
    public void should_not_throw_exception() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder()
                .amount(BigDecimal.ONE)
                .idUserTo("123")
                .idUserFrom("23233")
                .accountNumberTo(123L)
                .idWalletAccountFrom("2e3")
                .build();
        WalletAccount walletAccountTo = WalletAccount.builder().id("q1w2e3").build();
        when(walletAccountService.getWalletAccountByNumber(123L)).thenReturn(walletAccountTo);
        assertDoesNotThrow(() ->
                pixValidator.validate(asyncOperationMessage));
        verify(walletAccountService).getWalletAccountByNumber(123L);
    }

}