package br.com.dwallet.service.operation;

import br.com.dwallet.exception.WrongTransferTypeException;
import br.com.dwallet.model.dto.TransferDTO;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.queues.senders.OperationQueueSender;
import br.com.dwallet.service.operation.type.TransferType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransferService {

    private final Map<String, OperationQueueSender> operationSenders;

    public TransferService(Map<String, OperationQueueSender> operationSenders) {
        this.operationSenders = operationSenders;
    }

    public void doTransfer(TransferDTO transferDTO, String idUserFrom, String idWalletAccountFrom) {
        String type = transferDTO.getType();
        TransferType transferType = TransferType.getStrategyNameFor(type).orElseThrow(() -> new WrongTransferTypeException(String.format("Transfer type %s not exists", type)));

        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder()
                .idUserFrom(idUserFrom)
                .idWalletAccountFrom(idWalletAccountFrom)
                .idUserTo(transferDTO.getIdUserTo())
                .accountNumberTo(transferDTO.getAccountNumberTo())
                .amount(transferDTO.getAmount())
                .type(transferType.name())
                .build();

        operationSenders.get(transferType.getStrategyName()).sendMessage(asyncOperationMessage);
    }

}
