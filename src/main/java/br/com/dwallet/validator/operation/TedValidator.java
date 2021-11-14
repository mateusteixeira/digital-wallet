package br.com.dwallet.validator.operation;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import org.springframework.stereotype.Component;

@Component
public class TedValidator extends AbstractValidator implements Validator {

    public void validate(AsyncOperationMessage asyncOperationMessage) {
        validateUsersNotDifferent(asyncOperationMessage.getIdUserTo(), asyncOperationMessage.getIdUserFrom());
        validateWalletAccountNotDifferent(asyncOperationMessage.getIdWalletAccountFrom(), asyncOperationMessage.getAccountNumberTo());
        validateAmountBiggerThanZero(asyncOperationMessage.getAmount());
    }

}
