package br.com.dwallet.service;

import br.com.dwallet.exception.WalletAccountNotFoundException;
import br.com.dwallet.model.User;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.model.WalletAccountRepository;
import br.com.dwallet.model.dto.WalletAccountDTO;
import br.com.dwallet.translator.WalletAccountTranslator;
import br.com.dwallet.validator.WalletAccountValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletAccountService {

    private final UserService userService;

    private final WalletAccountValidator walletAccountValidator;
    
    private final WalletAccountRepository walletAccountRepository;
    
    private final WalletAccountTranslator walletAccountTranslator;

    public WalletAccountService(UserService userService, WalletAccountValidator walletAccountValidator, WalletAccountRepository walletAccountRepository, WalletAccountTranslator walletAccountTranslator) {
        this.userService = userService;
        this.walletAccountValidator = walletAccountValidator;
        this.walletAccountRepository = walletAccountRepository;
        this.walletAccountTranslator = walletAccountTranslator;
    }

    public WalletAccountDTO createWalletAccount(WalletAccountDTO walletAccountDTO) {
        User user = userService.getUserOrThrowNotFoundException(walletAccountDTO.getIdUser());
        WalletAccount walletAccount = walletAccountTranslator.fromDTO(walletAccountDTO);
        walletAccountValidator.validateWalletAccountExists(walletAccount);
        walletAccount.setBalance(BigDecimal.ZERO);
        walletAccount.setUser(user);
        return walletAccountTranslator.toDTO(walletAccountRepository.save(walletAccount));
    }

    public WalletAccountDTO getWalletAccountById(String idWalletAccount) {
        WalletAccount walletAccount = getWalletAccountOrThrowNotFoundException(idWalletAccount);
        return walletAccountTranslator.toDTO(walletAccount);
    }

    private WalletAccount getWalletAccountOrThrowNotFoundException(String idWalletAccount) {
        Optional<WalletAccount> walletAccountOp = walletAccountRepository.findById(idWalletAccount);
        return walletAccountOp.orElseThrow(() -> new WalletAccountNotFoundException(String.format("WalletAccount for id %s not found", idWalletAccount)));
    }

    public List<WalletAccountDTO> getAllWalletAccounts(Pageable paging) {
        return walletAccountRepository.findAll(paging).stream().map(walletAccountTranslator::toDTO).collect(Collectors.toList());
    }

    public void updateWalletAccount(WalletAccountDTO walletAccountDTO, String idWalletAccount) {
        WalletAccount walletAccount = getWalletAccountOrThrowNotFoundException(idWalletAccount);
        walletAccount.setAccountName(walletAccountDTO.getAccountName());
        walletAccountRepository.save(walletAccount);
    }

    public void deleteWalletAccount(String idWalletAccount) {
        WalletAccount walletAccount = getWalletAccountOrThrowNotFoundException(idWalletAccount);
        walletAccountRepository.delete(walletAccount);
    }

    public void deleteAllWalletAccounts() {
        walletAccountRepository.deleteAll();
    }
}
