package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationError;
import br.com.dwallet.model.OperationErrorRepository;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.WalletAccountService;
import br.com.dwallet.validator.operation.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class AbstractOperationService {

    private WalletAccountService walletAccountService;

    private OperationErrorRepository operationErrorRepository;

    private OperationTimeLineService operationTimeLineService;

    private WalletAccountBalanceService walletAccountBalanceService;

    private Validator validator;

    protected void doOperation(AsyncOperationMessage asyncOperationMessage) {
        try {
            validateOperation(asyncOperationMessage);
            WalletAccount walletAccountFromUpdated = subtractFromWalletAccount(asyncOperationMessage);
            WalletAccount walletAccountToUpdated = addToWalletAccount(asyncOperationMessage);
            walletAccountService.saveWalletAccount(walletAccountFromUpdated);
            walletAccountService.saveWalletAccount(walletAccountToUpdated);
            operationTimeLineService.createOperationTimeLineForOutComing(walletAccountFromUpdated, asyncOperationMessage.getAmount(), asyncOperationMessage.getType());
            operationTimeLineService.createOperationTimeLineForIncoming(walletAccountToUpdated, asyncOperationMessage.getAmount(), asyncOperationMessage.getType());
        } catch (Exception e) {
            OperationError operationError = OperationError.builder()
                    .error(e.getMessage())
                    .idUser(asyncOperationMessage.getIdUserFrom())
                    .idWalletAccount(asyncOperationMessage.getIdWalletAccountFrom())
                    .operationAt(LocalDateTime.now())
                    .type(asyncOperationMessage.getType())
                    .build();
            operationErrorRepository.save(operationError);
        }
    }

    protected void validateOperation(AsyncOperationMessage asyncOperationMessage) {
        validator.validate(asyncOperationMessage);
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    private WalletAccount addToWalletAccount(AsyncOperationMessage asyncOperationMessage) {
        WalletAccount walletAccountTo = walletAccountService.getWalletAccountByNumber(asyncOperationMessage.getAccountNumberTo());
        return walletAccountBalanceService.addAmountToWalletAccount(walletAccountTo, asyncOperationMessage.getAmount());
    }

    private WalletAccount subtractFromWalletAccount(AsyncOperationMessage asyncOperationMessage) {
        WalletAccount walletAccountFrom = walletAccountService.getWalletAccountByIdAndIdUser(asyncOperationMessage.getIdWalletAccountFrom(), asyncOperationMessage.getIdUserFrom());
        return walletAccountBalanceService.subtractAmountToWalletAccount(walletAccountFrom, asyncOperationMessage.getAmount());
    }

    @Autowired
    public void setWalletAccountService(WalletAccountService walletAccountService) {
        this.walletAccountService = walletAccountService;
    }

    @Autowired
    public void setWalletAccountBalanceService(WalletAccountBalanceService walletAccountBalanceService) {
        this.walletAccountBalanceService = walletAccountBalanceService;
    }

    @Autowired
    public void setOperationErrorRepository(OperationErrorRepository operationErrorRepository) {
        this.operationErrorRepository = operationErrorRepository;
    }

    @Autowired
    public void setOperationTimeLineService(OperationTimeLineService operationTimeLineService) {
        this.operationTimeLineService = operationTimeLineService;
    }
}
