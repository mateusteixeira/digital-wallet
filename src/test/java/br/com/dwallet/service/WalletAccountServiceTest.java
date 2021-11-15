package br.com.dwallet.service;

import br.com.dwallet.exception.WalletAccountNotFoundException;
import br.com.dwallet.model.User;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.model.dto.WalletAccountDTO;
import br.com.dwallet.model.repository.WalletAccountRepository;
import br.com.dwallet.translator.WalletAccountTranslator;
import br.com.dwallet.validator.WalletAccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WalletAccountServiceTest {
    private static final String ID_WALLET_ACCOUNT = "aqsw";

    @Mock
    private UserService userService;

    @Mock
    private WalletAccountValidator walletAccountValidator;

    @Mock
    private WalletAccountRepository walletAccountRepository;

    @Mock
    private WalletAccountTranslator walletAccountTranslator;

    @InjectMocks
    private WalletAccountService walletAccountService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_save_new_walletAccount() {
        WalletAccountDTO walletAccountDTO = WalletAccountDTO.builder().idUser("3232").build();
        WalletAccount walletAccount = mock(WalletAccount.class);
        when(userService.getUserOrThrowNotFoundException("3232")).thenReturn(User.builder().build());
        when(walletAccountTranslator.fromDTO(walletAccountDTO)).thenReturn(walletAccount);
        when(walletAccountRepository.save(walletAccount)).thenReturn(walletAccount);
        when(walletAccountTranslator.toDTO(walletAccount)).thenReturn(walletAccountDTO);

        WalletAccountDTO walletAccountCreatedDTO = walletAccountService.createWalletAccount(walletAccountDTO);
        assertNotNull(walletAccountCreatedDTO);

        verify(walletAccountValidator).validateWalletAccountExists(walletAccount);
        verify(walletAccountRepository).save(walletAccount);
    }

    @Test
    public void should_get_walletAccount_by_id() {
        WalletAccount walletAccount = WalletAccount.builder().build();
        WalletAccountDTO walletAccountDTO = WalletAccountDTO.builder().build();
        when(walletAccountRepository.findById(ID_WALLET_ACCOUNT)).thenReturn(Optional.of(walletAccount));
        when(walletAccountTranslator.toDTO(walletAccount)).thenReturn(walletAccountDTO);

        WalletAccountDTO walletAccountFindDTO = walletAccountService.getWalletAccountById(ID_WALLET_ACCOUNT);
        assertNotNull(walletAccountFindDTO);

        verify(walletAccountRepository).findById(ID_WALLET_ACCOUNT);
        verify(walletAccountTranslator).toDTO(walletAccount);
    }

    @Test
    public void should_throws_exception_when_get_walletAccount_by_id_not_found() {
        when(walletAccountRepository.findById(ID_WALLET_ACCOUNT)).thenReturn(Optional.empty());

        assertThrows(WalletAccountNotFoundException.class, () -> walletAccountService.getWalletAccountById(ID_WALLET_ACCOUNT));

        verify(walletAccountRepository).findById(ID_WALLET_ACCOUNT);
        verify(walletAccountTranslator, never()).toDTO(any());
    }

    @Test
    public void should_get_all_walletAccounts() {
        WalletAccount walletAccount = WalletAccount.builder().build();
        WalletAccountDTO walletAccountDTO = WalletAccountDTO.builder().build();
        PageImpl<WalletAccount> walletAccounts = new PageImpl<>(List.of(walletAccount));
        when(walletAccountRepository.findAll(Pageable.unpaged())).thenReturn(walletAccounts);
        when(walletAccountTranslator.toDTO(walletAccount)).thenReturn(walletAccountDTO);

        List<WalletAccountDTO> allWalletAccounts = walletAccountService.getAllWalletAccounts(Pageable.unpaged());
        assertNotNull(allWalletAccounts);
        assertEquals(1, allWalletAccounts.size());

        verify(walletAccountRepository).findAll(any(Pageable.class));
        verify(walletAccountTranslator).toDTO(walletAccount);
    }

    @Test
    public void should_update_walletAccount() {
        WalletAccount walletAccount = mock(WalletAccount.class);
        WalletAccountDTO walletAccountDTO = mock(WalletAccountDTO.class);
        when(walletAccountRepository.findById(ID_WALLET_ACCOUNT)).thenReturn(Optional.of(walletAccount));

        walletAccountService.updateWalletAccount(walletAccountDTO, ID_WALLET_ACCOUNT);
        verify(walletAccountRepository).findById(ID_WALLET_ACCOUNT);
        verify(walletAccountRepository).save(walletAccount);
    }

    @Test
    public void should_not_update_walletAccount_when_walletAccount_not_found() {
        when(walletAccountRepository.findById(ID_WALLET_ACCOUNT)).thenReturn(Optional.empty());

        assertThrows(WalletAccountNotFoundException.class, () -> walletAccountService.getWalletAccountById(ID_WALLET_ACCOUNT));

        verify(walletAccountRepository).findById(ID_WALLET_ACCOUNT);
        verify(walletAccountRepository, never()).save(any());
    }

    @Test
    public void should_delete_walletAccount_by_id() {
        walletAccountService.deleteWalletAccount(ID_WALLET_ACCOUNT);
        verify(walletAccountRepository).deleteById(ID_WALLET_ACCOUNT);
    }

    @Test
    public void should_delete_all_walletAccounts() {
        walletAccountService.deleteAllWalletAccounts();
        verify(walletAccountRepository).deleteAll();
    }
}