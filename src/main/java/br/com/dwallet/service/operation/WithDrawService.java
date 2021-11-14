package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationType;
import br.com.dwallet.validator.operation.WithDrawValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WithDrawService extends AbstractOutComingOperationService {

    private final WithDrawValidator withDrawValidator;

    public WithDrawService(WithDrawValidator withDrawValidator) {
        this.withDrawValidator = withDrawValidator;
    }

    public void doWithDraw(BigDecimal amount, String idUser, String idWalletAccount) {
        withDrawValidator.validateWithDraw(amount);
        super.doProcess(amount, idUser, idWalletAccount, OperationType.WITHDRAW.name());
    }
}
