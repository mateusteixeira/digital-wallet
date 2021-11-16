package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationType;
import br.com.dwallet.validator.operation.DepositValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class DepositService extends AbstractIncomingOperationService {

    private final DepositValidator depositValidator;

    public DepositService(DepositValidator depositValidator) {
        this.depositValidator = depositValidator;
    }

    public void doDeposit(BigDecimal amount, String idUser, String idWalletAccount) {
        log.info("DoDeposit with amount {} for user {} and idWalletAccount {}", amount, idUser, idWalletAccount);
        depositValidator.validateDeposit(amount);
        super.doProcess(idWalletAccount, idUser, amount, OperationType.DEPOSIT.name());
        log.info("Deposited with amount {} for user {} and idWalletAccount {}", amount, idUser, idWalletAccount);
    }
}
