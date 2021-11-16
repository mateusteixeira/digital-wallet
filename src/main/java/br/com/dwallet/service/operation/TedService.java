package br.com.dwallet.service.operation;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.validator.operation.TedValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TedService extends AbstractOperationService implements IOperationService {

    public void doProcess(AsyncOperationMessage asyncOperationMessage) {
        log.info("Making TED with: {}", asyncOperationMessage);
        super.doOperation(asyncOperationMessage);
        log.info("Done TED with: {}", asyncOperationMessage);
    }

    @Autowired
    public void setValidator(TedValidator tedValidator) {
        super.setValidator(tedValidator);
    }
}
