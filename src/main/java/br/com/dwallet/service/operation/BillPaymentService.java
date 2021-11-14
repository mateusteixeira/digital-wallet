package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationType;
import br.com.dwallet.validator.operation.BillPaymentValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BillPaymentService extends AbstractOutComingOperationService {

    private final BillPaymentValidator billPaymentValidator;

    public BillPaymentService(BillPaymentValidator billPaymentValidator) {
        this.billPaymentValidator = billPaymentValidator;
    }

    public void payBill(BigDecimal value, String idUser, String idWalletAccount) {
        billPaymentValidator.validateBillPayment(value);
        super.doProcess(value, idUser, idWalletAccount, OperationType.BILL_PAYMENT.name());
    }
}
