package br.com.dwallet.service.operation;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.validator.operation.PixValidator;
import br.com.dwallet.validator.operation.TedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TedService extends AbstractOperationService implements Operation {

    public void doProcess(AsyncOperationMessage asyncOperationMessage) {
        super.doOperation(asyncOperationMessage);
    }

    @Autowired
    public void setValidator(TedValidator tedValidator) {
        super.setValidator(tedValidator);
    }
}
