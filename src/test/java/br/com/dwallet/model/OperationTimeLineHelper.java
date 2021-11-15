package br.com.dwallet.model;

public class OperationTimeLineHelper {

    public static OperationTimeLine withUser(User user) {
        return OperationTimeLine.builder()
                .user(user)
                .build();
    }

    public static OperationTimeLine withUserAndWalletAccount(User user, WalletAccount walletAccount) {
        return OperationTimeLine.builder()
                .user(user)
                .walletAccount(walletAccount)
                .build();
    }

}
