package br.com.dwallet.service.operation;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.validator.operation.PixValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PixService extends AbstractOperationService implements IOperationService {

    public void doProcess(AsyncOperationMessage asyncOperationMessage) {
        log.info("Making PIX with: {}", asyncOperationMessage);
        super.doOperation(asyncOperationMessage);
        log.info("Done PIX with: {}", asyncOperationMessage);
    }

    @Autowired
    public void setValidator(PixValidator pixValidator) {
        super.setValidator(pixValidator);
    }
}
