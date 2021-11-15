package br.com.dwallet.validator;

import br.com.dwallet.exception.WalletAccountAlreadyExistsException;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.model.repository.WalletAccountRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class WalletAccountValidator {

    private final WalletAccountRepository walletAccountRepository;

    public WalletAccountValidator(WalletAccountRepository walletAccountRepository) {
        this.walletAccountRepository = walletAccountRepository;
    }

    public void validateWalletAccountExists(WalletAccount walletAccount) {
        WalletAccount existentWalletAccount = walletAccountRepository.findByAccountNumber(walletAccount.getAccountNumber());
        if (Objects.nonNull(existentWalletAccount)) {
            throw new WalletAccountAlreadyExistsException((String.format("Wallet Account already exists for number %s with name %s", existentWalletAccount.getAccountNumber(), existentWalletAccount.getAccountName())));
        }
    }

}
