package br.com.dwallet.translator;

import br.com.dwallet.model.OperationError;
import br.com.dwallet.model.dto.OperationErrorDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IOperationServiceErrorTranslatorTest {

    private final OperationErrorTranslator operationErrorTranslator = new OperationErrorTranslator();

    @Test
    public void should_assert_translate() {
        OperationError operationError = OperationError.builder()
                .operationAt(LocalDateTime.now())
                .error("someError")
                .type("TED")
                .idUser("1234")
                .idWalletAccount("aqswde")
                .build();

        OperationErrorDTO operationErrorDTO = operationErrorTranslator.toDTO(operationError, "Conta1");
        assertNotNull(operationErrorDTO);
        assertEquals(operationError.getOperationAt(), operationErrorDTO.getOperationAt());
        assertEquals(operationError.getError(), operationErrorDTO.getError());
        assertEquals(operationError.getType(), operationErrorDTO.getType());
        assertEquals("Conta1", operationErrorDTO.getAccountName());
    }
}