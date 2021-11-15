package br.com.dwallet.api;

import br.com.dwallet.model.dto.LifeTimeDTO;
import br.com.dwallet.model.dto.OperationErrorDTO;
import br.com.dwallet.model.dto.WalletAccountLifeTimeDTO;
import br.com.dwallet.service.LifeTimeService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class LifeTimeAPITest {

    private static final String ID_USER = "q1w2e3r4";
    private static final String ID_WALLET_ACCOUNT = "AqSwDe";

    @Mock
    private LifeTimeService lifeTimeService;

    @InjectMocks
    private LifeTimeAPI lifeTimeAPI;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_get_life_time_for_id_user_paged() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        when(lifeTimeService.getLifeTime(ID_USER, pageRequest)).thenReturn(LifeTimeDTO.builder().build());

        ResponseEntity<LifeTimeDTO> lifeTimeDTOResponseEntity = lifeTimeAPI.getLifeTime(ID_USER, pageRequest);
        assertNotNull(lifeTimeDTOResponseEntity);
        LifeTimeDTO lifeTimeDTO = lifeTimeDTOResponseEntity.getBody();
        assertNotNull(lifeTimeDTO);
        verify(lifeTimeService).getLifeTime(eq(ID_USER), any(PageRequest.class));
    }

    @Test
    public void should_get_life_time_for_id_user_and_id_wallet_account_paged() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        when(lifeTimeService.getWalletAccountLifeTime(ID_USER, ID_WALLET_ACCOUNT, pageRequest)).thenReturn(WalletAccountLifeTimeDTO.builder().build());

        ResponseEntity<WalletAccountLifeTimeDTO> walletAccountLifeTimeDTOResponseEntity = lifeTimeAPI.getWalletAccountLifeTime(ID_USER, ID_WALLET_ACCOUNT, pageRequest);
        assertNotNull(walletAccountLifeTimeDTOResponseEntity);
        WalletAccountLifeTimeDTO walletAccountLifeTimeDTO = walletAccountLifeTimeDTOResponseEntity.getBody();
        assertNotNull(walletAccountLifeTimeDTO);
        verify(lifeTimeService).getWalletAccountLifeTime(eq(ID_USER), eq(ID_WALLET_ACCOUNT), any(PageRequest.class));
    }

    @Test
    public void should_get_life_time_error_for_id_user() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        when(lifeTimeService.getErrorsByUser(ID_USER, pageRequest)).thenReturn(Lists.newArrayList(OperationErrorDTO.builder().build()));

        ResponseEntity<List<OperationErrorDTO>> operationErrorsDTOResponseEntity = lifeTimeAPI.getOperationErrors(ID_USER, pageRequest);
        assertNotNull(operationErrorsDTOResponseEntity.getBody());
        assertEquals(1, operationErrorsDTOResponseEntity.getBody().size());
        OperationErrorDTO operationErrorDTO = operationErrorsDTOResponseEntity.getBody().get(0);
        assertNotNull(operationErrorDTO);
        verify(lifeTimeService).getErrorsByUser(eq(ID_USER), any(PageRequest.class));
    }

}