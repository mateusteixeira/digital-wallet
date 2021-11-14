package br.com.dwallet.service.operation;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.validator.operation.Validator;

public interface Operation {

    void setValidator(Validator validator);
}
