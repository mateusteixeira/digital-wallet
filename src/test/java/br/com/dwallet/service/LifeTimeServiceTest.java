package br.com.dwallet.service;

import br.com.dwallet.model.OperationTimeLine;
import br.com.dwallet.model.dto.LifeTimeDTO;
import br.com.dwallet.model.dto.OperationLifeTimeDTO;
import br.com.dwallet.model.dto.WalletAccountDTO;
import br.com.dwallet.model.dto.WalletAccountLifeTimeDTO;
import br.com.dwallet.service.operation.OperationTimeLineService;
import br.com.dwallet.translator.LifeTimeTranslator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class LifeTimeServiceTest {

    private static final String ID_USER = "123a";
    private static final String ID_WALLET_ACCOUNT = "AQSWD";

    @Mock
    private LifeTimeTranslator lifeTimeTranslator;

    @Mock
    private WalletAccountService walletAccountService;

    @Mock
    private OperationTimeLineService operationTimeLineService;

    @InjectMocks
    private LifeTimeService lifeTimeService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_get_life_time() {
        OperationTimeLine operationTimeLine = OperationTimeLine.builder().build();
        OperationLifeTimeDTO operationLifeTimeDTO = OperationLifeTimeDTO.builder().build();
        when(operationTimeLineService.getByIdUser(ID_USER, Pageable.unpaged())).thenReturn(List.of(operationTimeLine));
        when(lifeTimeTranslator.toOperationLifeTimeDTO(operationTimeLine)).thenReturn(operationLifeTimeDTO);

        LifeTimeDTO lifeTimeDTO = lifeTimeService.getLifeTime(ID_USER, Pageable.unpaged());
        assertNotNull(lifeTimeDTO);
        assertEquals(1, lifeTimeDTO.getOperationQuantity());
        List<OperationLifeTimeDTO> operationLifeTimeDTOS = lifeTimeDTO.getOperationLifeTimeDTOS();
        assertNotNull(operationLifeTimeDTOS);
        assertEquals(1, operationLifeTimeDTOS.size());
        assertEquals(operationLifeTimeDTO, operationLifeTimeDTOS.get(0));

        verify(operationTimeLineService).getByIdUser(ID_USER, Pageable.unpaged());
        verify(lifeTimeTranslator).toOperationLifeTimeDTO(operationTimeLine);
    }

    @Test
    public void should_get_life_time_with_no_operation_when_not_exists() {
        when(operationTimeLineService.getByIdUser(ID_USER, Pageable.unpaged())).thenReturn(Lists.emptyList());

        LifeTimeDTO lifeTimeDTO = lifeTimeService.getLifeTime(ID_USER, Pageable.unpaged());
        assertNotNull(lifeTimeDTO);
        assertEquals(0, lifeTimeDTO.getOperationQuantity());
        List<OperationLifeTimeDTO> operationLifeTimeDTOS = lifeTimeDTO.getOperationLifeTimeDTOS();
        assertNotNull(operationLifeTimeDTOS);
        assertEquals(0, operationLifeTimeDTOS.size());

        verify(operationTimeLineService).getByIdUser(ID_USER, Pageable.unpaged());
        verify(lifeTimeTranslator, never()).toOperationLifeTimeDTO(any());
    }

    @Test
    public void should_get_wallet_account_life_time() {
        OperationTimeLine operationTimeLine = OperationTimeLine.builder().build();
        OperationLifeTimeDTO operationLifeTimeDTO = OperationLifeTimeDTO.builder().build();
        WalletAccountDTO walletAccount = WalletAccountDTO.builder().build();
        when(walletAccountService.getWalletAccountById(ID_WALLET_ACCOUNT)).thenReturn(walletAccount);
        when(operationTimeLineService.getByIdUserAndIdWalletAccount(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged())).thenReturn(List.of(operationTimeLine));
        when(lifeTimeTranslator.toOperationLifeTimeDTOWithOutAccountName(operationTimeLine)).thenReturn(operationLifeTimeDTO);

        WalletAccountLifeTimeDTO walletAccountLifeTime = lifeTimeService.getWalletAccountLifeTime(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged());
        assertNotNull(walletAccountLifeTime);
        assertEquals(1, walletAccountLifeTime.getOperationQuantity());
        List<OperationLifeTimeDTO> operationLifeTimeDTOS = walletAccountLifeTime.getOperationLifeTimeDTOS();
        assertNotNull(operationLifeTimeDTOS);
        assertEquals(1, operationLifeTimeDTOS.size());
        assertEquals(operationLifeTimeDTO, operationLifeTimeDTOS.get(0));

        verify(operationTimeLineService).getByIdUserAndIdWalletAccount(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged());
        verify(lifeTimeTranslator).toOperationLifeTimeDTOWithOutAccountName(operationTimeLine);
    }

    @Test
    public void should_get_wallet_account_life_time_with_no_operation_when_not_exists() {
        when(operationTimeLineService.getByIdUserAndIdWalletAccount(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged())).thenReturn(Lists.emptyList());
        WalletAccountDTO walletAccount = WalletAccountDTO.builder().build();
        when(walletAccountService.getWalletAccountById(ID_WALLET_ACCOUNT)).thenReturn(walletAccount);

        WalletAccountLifeTimeDTO walletAccountLifeTime = lifeTimeService.getWalletAccountLifeTime(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged());
        assertNotNull(walletAccountLifeTime);
        assertEquals(0, walletAccountLifeTime.getOperationQuantity());
        List<OperationLifeTimeDTO> operationLifeTimeDTOS = walletAccountLifeTime.getOperationLifeTimeDTOS();
        assertNotNull(operationLifeTimeDTOS);
        assertEquals(0, operationLifeTimeDTOS.size());

        verify(operationTimeLineService).getByIdUserAndIdWalletAccount(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged());
        verify(lifeTimeTranslator, never()).toOperationLifeTimeDTOWithOutAccountName(any());
    }

}