package br.com.dwallet.api;

import br.com.dwallet.model.dto.WalletAccountDTO;
import br.com.dwallet.service.WalletAccountService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WalletAccountAPITest {

    private static final String ID_USER = "q1w2e3r4";
    private static final String ID_WALLET_ACCOUNT = "AqSwDe";

    @Mock
    private WalletAccountService walletAccountService;

    @InjectMocks
    private WalletAccountAPI walletAccountAPI;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_create_walletAccount_with_location_created() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        WalletAccountDTO walletAccountDTO = mock(WalletAccountDTO.class);
        when(walletAccountService.createWalletAccount(walletAccountDTO)).thenReturn(walletAccountDTO);
        when(walletAccountDTO.getId()).thenReturn(ID_WALLET_ACCOUNT);

        ResponseEntity<WalletAccountDTO> walletAccountDTOResponseEntity = walletAccountAPI.createWalletAccount(walletAccountDTO, ID_USER);
        assertNotNull(walletAccountDTOResponseEntity);
        assertEquals(HttpStatus.CREATED, walletAccountDTOResponseEntity.getStatusCode());
        assertNotNull(walletAccountDTOResponseEntity.getHeaders());
        assertNotNull(walletAccountDTOResponseEntity.getHeaders().getLocation());
        assertEquals("/" + ID_WALLET_ACCOUNT, walletAccountDTOResponseEntity.getHeaders().getLocation().getPath());
        assertNotNull(walletAccountDTOResponseEntity.getBody());
        verify(walletAccountService).createWalletAccount(walletAccountDTO);
        verify(walletAccountDTO).getId();
    }

    @Test
    public void should_get_all_walletAccounts_with_ok() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        when(walletAccountService.getAllWalletAccounts(pageRequest)).thenReturn(Lists.newArrayList(WalletAccountDTO.builder().build()));

        ResponseEntity<List<WalletAccountDTO>> walletAccountsResponseEntity = walletAccountAPI.getWalletAccounts(pageRequest);
        assertNotNull(walletAccountsResponseEntity);
        assertEquals(HttpStatus.OK, walletAccountsResponseEntity.getStatusCode());
        assertNotNull(walletAccountsResponseEntity.getBody());
        verify(walletAccountService).getAllWalletAccounts(any(PageRequest.class));
    }

    @Test
    public void should_get_all_walletAccount_by_id_with_ok() {
        when(walletAccountService.getWalletAccountById(ID_USER)).thenReturn(WalletAccountDTO.builder().build());

        ResponseEntity<WalletAccountDTO> walletAccountsResponseEntity = walletAccountAPI.getWalletAccount(ID_USER);
        assertNotNull(walletAccountsResponseEntity);
        assertEquals(HttpStatus.OK, walletAccountsResponseEntity.getStatusCode());
        assertNotNull(walletAccountsResponseEntity.getBody());
        verify(walletAccountService).getWalletAccountById(ID_USER);
    }

    @Test
    public void should_update_walletAccount_with_no_content() {
        WalletAccountDTO walletAccountDTO = WalletAccountDTO.builder().build();
        ResponseEntity<Object> responseEntity = walletAccountAPI.updateWalletAccount(walletAccountDTO, ID_USER);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(walletAccountService).updateWalletAccount(eq(walletAccountDTO), eq(ID_USER));
    }

    @Test
    public void should_delete_walletAccount_by_id_with_ok() {
        ResponseEntity<Object> responseEntity = walletAccountAPI.deleteWalletAccount(ID_USER);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(walletAccountService).deleteWalletAccount(ID_USER);
    }

    @Test
    public void should_delete_all_walletAccounts_with_ok() {
        ResponseEntity<Object> responseEntity = walletAccountAPI.deleteAllWalletAccounts();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(walletAccountService).deleteAllWalletAccounts();
    }
}