package br.com.dwallet.validator.operation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DepositValidator extends AbstractValidator {

    public void validateDeposit(BigDecimal amount) {
        validateAmountBiggerThanZero(amount);
    }

}
