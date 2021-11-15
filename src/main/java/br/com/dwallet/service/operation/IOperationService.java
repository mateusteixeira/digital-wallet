package br.com.dwallet.service.operation;

import br.com.dwallet.validator.operation.Validator;

public interface IOperationService {

    void setValidator(Validator validator);
}
