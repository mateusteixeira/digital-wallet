package br.com.dwallet.validator.operation;

import br.com.dwallet.exception.AmountBusinessException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WithDrawValidatorTest {

    private final WithDrawValidator withDrawValidator = new WithDrawValidator();

    @Test
    public void should_throw_exception_when_amount_less_than_zero() {
        assertThrows(AmountBusinessException.class, () ->
                withDrawValidator.validateWithDraw(new BigDecimal("-1")));
    }

    @Test
    public void should_throw_exception_when_amount_equal_zero() {
        assertThrows(AmountBusinessException.class, () ->
                withDrawValidator.validateWithDraw(BigDecimal.ZERO));
    }

    @Test
    public void should_throw_exception_when_amount_greater_than_zero() {
        assertDoesNotThrow(() -> withDrawValidator.validateWithDraw(BigDecimal.ONE));
    }

}