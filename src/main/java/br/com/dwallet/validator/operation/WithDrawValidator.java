package br.com.dwallet.validator.operation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WithDrawValidator extends AbstractValidator {

    public void validateWithDraw(BigDecimal amount) {
        validateAmountBiggerThanZero(amount);
    }


}
