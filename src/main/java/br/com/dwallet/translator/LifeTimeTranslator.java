package br.com.dwallet.translator;

import br.com.dwallet.model.OperationTimeLine;
import br.com.dwallet.model.dto.OperationLifeTimeDTO;
import org.springframework.stereotype.Component;

@Component
public class LifeTimeTranslator {

    public OperationLifeTimeDTO toOperationLifeTimeDTO(OperationTimeLine operationTimeLine) {
        return OperationLifeTimeDTO.builder()
                .accountName(operationTimeLine.getWalletAccount().getAccountName())
                .type(operationTimeLine.getType())
                .incoming(operationTimeLine.isIncoming())
                .operationAt(operationTimeLine.getOperationAt())
                .value(operationTimeLine.getValue())
                .build();
    }

    public OperationLifeTimeDTO toOperationLifeTimeDTOWithOutAccountName(OperationTimeLine operationTimeLine) {
        return OperationLifeTimeDTO.builder()
                .type(operationTimeLine.getType())
                .incoming(operationTimeLine.isIncoming())
                .operationAt(operationTimeLine.getOperationAt())
                .value(operationTimeLine.getValue())
                .build();
    }

}
