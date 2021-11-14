package br.com.dwallet.validator.operation;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import org.springframework.stereotype.Component;

@Component
public class PixValidator extends AbstractValidator implements Validator {

    public void validate(AsyncOperationMessage asyncOperationMessage) {
        validateWalletAccountNotSame(asyncOperationMessage.getIdWalletAccountFrom(), asyncOperationMessage.getAccountNumberTo());
        validateAmountBiggerThanZero(asyncOperationMessage.getAmount());
    }

}
