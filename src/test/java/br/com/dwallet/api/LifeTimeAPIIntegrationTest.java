package br.com.dwallet.api;

import br.com.dwallet.model.dto.LifeTimeDTO;
import br.com.dwallet.model.dto.OperationErrorDTO;
import br.com.dwallet.model.dto.WalletAccountLifeTimeDTO;
import br.com.dwallet.service.LifeTimeService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LifeTimeAPI.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = {LifeTimeAPI.class})
class LifeTimeAPIIntegrationTest {

    private static final String ID_USER = "134";
    private static final String ID_WALLET_ACCOUNT = "13434";

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private LifeTimeService lifeTimeService;

    @Test
    public void should_get_life_time() throws Exception {
        LifeTimeDTO lifeTimeDTO = LifeTimeDTO.builder().build();
        when(lifeTimeService.getLifeTime(ID_USER, Pageable.unpaged())).thenReturn(lifeTimeDTO);

        mockMvc.perform(get("/{idUser}/operation/lifetime", ID_USER)
                        .header("Content-Type", MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(lifeTimeService).getLifeTime(any(), any());
    }

    @Test
    public void should_get_wallet_account_life_time() throws Exception {
        WalletAccountLifeTimeDTO walletAccountLifeTimeDTO = WalletAccountLifeTimeDTO.builder().build();
        when(lifeTimeService.getWalletAccountLifeTime(ID_USER, ID_WALLET_ACCOUNT, Pageable.unpaged())).thenReturn(walletAccountLifeTimeDTO);

        mockMvc.perform(get("/{idUser}/operation/walletaccountlifetime/{idWalletAccount}", ID_USER, ID_WALLET_ACCOUNT)
                        .header("Content-Type", MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(lifeTimeService).getWalletAccountLifeTime(any(), any(), any());
    }

    @Test
    public void should_get_operation_error() throws Exception {
        OperationErrorDTO operationErrorDTO = OperationErrorDTO.builder().build();
        when(lifeTimeService.getErrorsByUser(ID_USER, Pageable.unpaged())).thenReturn(Lists.newArrayList(operationErrorDTO));

        mockMvc.perform(get("/{idUser}/operation/errors", ID_USER)
                        .header("Content-Type", MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(lifeTimeService).getErrorsByUser(any(), any());
    }

}