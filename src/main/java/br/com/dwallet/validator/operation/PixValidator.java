package br.com.dwallet.validator.operation;

import br.com.dwallet.exception.TransferBusinessException;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.WalletAccountService;
import org.springframework.stereotype.Component;

@Component
public class PixValidator extends AbstractValidator implements Validator{

    public void validate(AsyncOperationMessage asyncOperationMessage) {
        validateWalletAccountNotSame(asyncOperationMessage.getIdWalletAccountFrom(), asyncOperationMessage.getAccountNumberTo());
        validateAmountBiggerThanZero(asyncOperationMessage.getAmount());
    }

}
