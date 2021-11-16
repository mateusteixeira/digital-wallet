package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationType;
import br.com.dwallet.validator.operation.BillPaymentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class BillPaymentService extends AbstractOutComingOperationService {

    private final BillPaymentValidator billPaymentValidator;

    public BillPaymentService(BillPaymentValidator billPaymentValidator) {
        this.billPaymentValidator = billPaymentValidator;
    }

    public void payBill(BigDecimal value, String idUser, String idWalletAccount) {
        log.info("Paybill with value {} for idUser {} and idWalletAccount: {}", value, idUser, idWalletAccount);
        billPaymentValidator.validateBillPayment(value);
        super.doProcess(value, idUser, idWalletAccount, OperationType.BILL_PAYMENT.name());
        log.info("Bill paid with value {} for idUser {} and idWalletAccount: {}", value, idUser, idWalletAccount);
    }
}
