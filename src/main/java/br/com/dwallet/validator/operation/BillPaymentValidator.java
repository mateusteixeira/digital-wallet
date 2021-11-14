package br.com.dwallet.validator.operation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BillPaymentValidator extends AbstractValidator {

    public void validateBillPayment(BigDecimal amount) {
        validateAmountBiggerThanZero(amount);
    }
}
