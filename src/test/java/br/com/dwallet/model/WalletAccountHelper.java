package br.com.dwallet.model;

public class WalletAccountHelper {

    public static WalletAccount walletAccount() {
        return WalletAccount.builder().build();
    }

    public static WalletAccount walletAccountWithNumber(Long accountNumber) {
        return WalletAccount.builder().accountNumber(accountNumber).build();
    }

    public static WalletAccount walletAccountWithUser(User user) {
        return WalletAccount.builder().user(user).build();
    }
}
