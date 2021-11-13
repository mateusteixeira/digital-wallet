package br.com.dwallet.exception;

public class CardAlreadyExistsException extends RuntimeException {

    public CardAlreadyExistsException(String message) {
        super(message);
    }
}
