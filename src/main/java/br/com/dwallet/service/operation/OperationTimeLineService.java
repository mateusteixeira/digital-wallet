package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationTimeLine;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.model.repository.OperationTimeLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OperationTimeLineService {

    private final OperationTimeLineRepository operationTimeLineRepository;

    public OperationTimeLineService(OperationTimeLineRepository operationTimeLineRepository) {
        this.operationTimeLineRepository = operationTimeLineRepository;
    }

    public void createOperationTimeLineForIncoming(WalletAccount walletAccount, BigDecimal value, String type) {
        createOperationTimeLine(walletAccount, value, type, true);
    }

    public void createOperationTimeLineForOutComing(WalletAccount walletAccount, BigDecimal value, String type) {
        createOperationTimeLine(walletAccount, value, type, false);
    }

    private void createOperationTimeLine(WalletAccount walletAccount, BigDecimal value, String type, boolean incoming) {
        log.info("Creating Operation Time line for user {} wallet {} with type {} for incoming {}", walletAccount.getUser().getId(), walletAccount.getAccountNumber(), type, incoming);
        OperationTimeLine operationTimeLine = OperationTimeLine.builder()
                .user(walletAccount.getUser())
                .walletAccount(walletAccount)
                .incoming(incoming)
                .value(value)
                .operationAt(LocalDateTime.now())
                .type(type)
                .build();

        operationTimeLineRepository.save(operationTimeLine);
        log.info("Created Operation Time line for user {} wallet {} with type {} for incoming {}", walletAccount.getUser().getId(), walletAccount.getAccountNumber(), type, incoming);
    }

    public List<OperationTimeLine> getByIdUser(String idUser, Pageable paging) {
        return operationTimeLineRepository.findByUserId(idUser, paging);
    }

    public List<OperationTimeLine> getByIdUserAndIdWalletAccount(String idUser, String idWalletAccount, Pageable paging) {
        return operationTimeLineRepository.findByUserIdAndWalletAccountId(idUser, idWalletAccount, paging);
    }
}
