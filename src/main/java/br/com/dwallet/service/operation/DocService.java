package br.com.dwallet.service.operation;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.validator.operation.DocValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocService extends AbstractOperationService implements IOperationService {

    public void doProcess(AsyncOperationMessage asyncOperationMessage) {
        super.doOperation(asyncOperationMessage);
    }

    @Autowired
    public void setValidator(DocValidator docValidator) {
        super.setValidator(docValidator);
    }
}
