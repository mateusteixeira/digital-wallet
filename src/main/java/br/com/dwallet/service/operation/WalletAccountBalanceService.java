package br.com.dwallet.service.operation;

import br.com.dwallet.model.WalletAccount;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletAccountBalanceService {

    public WalletAccount addAmountToWalletAccount(WalletAccount walletAccount, BigDecimal amount) {
        BigDecimal newBalance = walletAccount.getBalance().add(amount);
        walletAccount.setBalance(newBalance);
        return walletAccount;
    }

    public WalletAccount subtractAmountToWalletAccount(WalletAccount walletAccount, BigDecimal amount) {
        BigDecimal newBalance = walletAccount.getBalance().subtract(amount);
        walletAccount.setBalance(newBalance);
        return walletAccount;
    }
}
