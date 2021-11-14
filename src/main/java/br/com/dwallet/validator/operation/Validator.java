package br.com.dwallet.validator.operation;

import br.com.dwallet.queues.messages.AsyncOperationMessage;

public interface Validator {

    void validate(AsyncOperationMessage asyncOperationMessage);
}
