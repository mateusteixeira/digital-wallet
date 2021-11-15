package br.com.dwallet.translator;

import br.com.dwallet.model.OperationError;
import br.com.dwallet.model.dto.OperationErrorDTO;
import org.springframework.stereotype.Component;

@Component
public class OperationErrorTranslator {

    public OperationErrorDTO toDTO(OperationError operationError, String accountName) {
        return OperationErrorDTO.builder()
                .accountName(accountName)
                .operationAt(operationError.getOperationAt())
                .type(operationError.getType())
                .error(operationError.getError())
                .build();
    }
}
