package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationTimeLine;
import br.com.dwallet.model.User;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.model.repository.OperationTimeLineRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OperationTimeLineServiceTest {

    private static final String ID_USER = "a1de";
    private static final String ID_WALLET_ACCOUNT = "a21de";

    @Mock
    private OperationTimeLineRepository operationTimeLineRepository;

    @InjectMocks
    private OperationTimeLineService operationTimeLineService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_get_operations_for_user() {
        OperationTimeLine operationTimeLine = OperationTimeLine.builder().build();
        when(operationTimeLineRepository.findByUserId(ID_USER, Pageable.unpaged())).thenReturn(Lists.newArrayList(operationTimeLine));

        List<OperationTimeLine> operationTimeLines = operationTimeLineService.getByIdUser(ID_USER, Pageable.unpaged());
        assertNotNull(operationTimeLines);
        assertEquals(operationTimeLine, operationTimeLines.get(0));
        verify(operationTimeLineRepository).findByUserId(ID_USER, Pageable.unpaged());
    }

    @Test
    public void should_get_operations_for_wallet_account() {
        OperationTimeLine operationTimeLine = OperationTimeLine.builder().build();
        when(operationTimeLineRepository.findByUserIdAndWalletAccountId(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged())).thenReturn(Lists.newArrayList(operationTimeLine));

        List<OperationTimeLine> operationTimeLines = operationTimeLineService.getByIdUserAndIdWalletAccount(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged());
        assertNotNull(operationTimeLines);
        assertEquals(operationTimeLine, operationTimeLines.get(0));
        verify(operationTimeLineRepository).findByUserIdAndWalletAccountId(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged());
    }

    @Test
    public void should_create_operation_time_for_incoming() {
        WalletAccount walletAccount = WalletAccount.builder().user(User.builder().build()).build();

        operationTimeLineService.createOperationTimeLineForIncoming(walletAccount, BigDecimal.ZERO, "TED");

        ArgumentCaptor<OperationTimeLine> operationTimeLineArgumentCaptor = ArgumentCaptor.forClass(OperationTimeLine.class);
        verify(operationTimeLineRepository).save(operationTimeLineArgumentCaptor.capture());
        OperationTimeLine operationTimeLine = operationTimeLineArgumentCaptor.getValue();
        assertNotNull(operationTimeLine);
        assertEquals(BigDecimal.ZERO, operationTimeLine.getValue());
        assertEquals("TED", operationTimeLine.getType());
        assertTrue(operationTimeLine.isIncoming());
    }

    @Test
    public void should_create_operation_time_for_out_coming() {
        WalletAccount walletAccount = WalletAccount.builder().user(User.builder().build()).build();

        operationTimeLineService.createOperationTimeLineForOutComing(walletAccount, BigDecimal.ZERO, "TED");

        ArgumentCaptor<OperationTimeLine> operationTimeLineArgumentCaptor = ArgumentCaptor.forClass(OperationTimeLine.class);
        verify(operationTimeLineRepository).save(operationTimeLineArgumentCaptor.capture());
        OperationTimeLine operationTimeLine = operationTimeLineArgumentCaptor.getValue();
        assertNotNull(operationTimeLine);
        assertEquals(BigDecimal.ZERO, operationTimeLine.getValue());
        assertEquals("TED", operationTimeLine.getType());
        assertFalse(operationTimeLine.isIncoming());
    }

}