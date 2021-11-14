package br.com.dwallet.exception;

public class WrongTransferTypeException extends RuntimeException {

    public WrongTransferTypeException(String message) {
        super(message);
    }
}
