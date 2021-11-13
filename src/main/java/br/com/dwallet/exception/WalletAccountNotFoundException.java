package br.com.dwallet.exception;

public class WalletAccountNotFoundException extends RuntimeException {

    public WalletAccountNotFoundException(String message) {
        super(message);
    }
}
