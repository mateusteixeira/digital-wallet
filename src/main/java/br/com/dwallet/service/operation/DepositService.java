package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationType;
import br.com.dwallet.validator.operation.DepositValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositService extends AbstractIncomingOperationService{

    private final DepositValidator depositValidator;

    public DepositService(DepositValidator depositValidator) {
        this.depositValidator = depositValidator;
    }

    public void doDeposit(BigDecimal amount, String idUser, String idWalletAccount) {
        depositValidator.validateDeposit(amount);
       super.doProcess(idWalletAccount, idUser, amount, OperationType.DEPOSIT.name());
    }
}
