package br.com.dwallet.translator;

import br.com.dwallet.model.OperationTimeLine;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.model.dto.OperationLifeTimeDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LifeTimeTranslatorTest {

    private final LifeTimeTranslator lifeTimeTranslator = new LifeTimeTranslator();

    @Test
    public void should_translate_to_dto() {
        OperationTimeLine operationTimeLine = OperationTimeLine.builder()
                .type("PIX")
                .operationAt(LocalDateTime.now())
                .value(BigDecimal.ZERO)
                .incoming(true)
                .walletAccount(WalletAccount.builder().accountName("conta1").build())
                .build();

        OperationLifeTimeDTO operationLifeTimeDTO = lifeTimeTranslator.toOperationLifeTimeDTO(operationTimeLine);
        assertNotNull(operationLifeTimeDTO);
        assertEquals(operationTimeLine.getOperationAt(), operationLifeTimeDTO.getOperationAt());
        assertEquals(operationTimeLine.getType(), operationLifeTimeDTO.getType());
        assertEquals(operationTimeLine.getValue(), operationLifeTimeDTO.getValue());
        assertEquals(operationTimeLine.getWalletAccount().getAccountName(), operationLifeTimeDTO.getAccountName());
        assertTrue(operationLifeTimeDTO.isIncoming());
    }

    @Test
    public void should_translate_to_dto_without_account_name() {
        OperationTimeLine operationTimeLine = OperationTimeLine.builder()
                .type("PIX")
                .operationAt(LocalDateTime.now())
                .value(BigDecimal.ZERO)
                .incoming(true)
                .walletAccount(WalletAccount.builder().accountName("conta1").build())
                .build();

        OperationLifeTimeDTO operationLifeTimeDTO = lifeTimeTranslator.toOperationLifeTimeDTOWithOutAccountName(operationTimeLine);
        assertNotNull(operationLifeTimeDTO);
        assertEquals(operationTimeLine.getOperationAt(), operationLifeTimeDTO.getOperationAt());
        assertEquals(operationTimeLine.getType(), operationLifeTimeDTO.getType());
        assertEquals(operationTimeLine.getValue(), operationLifeTimeDTO.getValue());
        assertNull(operationLifeTimeDTO.getAccountName());
        assertTrue(operationLifeTimeDTO.isIncoming());
    }
}