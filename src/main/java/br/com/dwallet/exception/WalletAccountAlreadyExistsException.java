package br.com.dwallet.exception;

public class WalletAccountAlreadyExistsException extends RuntimeException {

    public WalletAccountAlreadyExistsException(String message) {
        super(message);
    }
}
