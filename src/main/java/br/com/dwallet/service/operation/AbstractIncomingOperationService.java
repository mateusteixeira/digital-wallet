package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationType;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.service.WalletAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public abstract class AbstractIncomingOperationService {

    private WalletAccountService walletAccountService;

    private OperationTimeLineService operationTimeLineService;

    private WalletAccountBalanceService walletAccountBalanceService;

    public void doProcess(String idWalletAccount, String idUser, BigDecimal amount, String type) {
        WalletAccount walletAccount = walletAccountService.getWalletAccountByIdAndIdUser(idWalletAccount, idUser);
        WalletAccount walletAccountUpdate = walletAccountBalanceService.addAmountToWalletAccount(walletAccount, amount);
        walletAccountService.saveWalletAccount(walletAccountUpdate);
        operationTimeLineService.createOperationTimeLineForIncoming(walletAccount, amount, type);
    }


    @Autowired
    public void setWalletAccountService(WalletAccountService walletAccountService) {
        this.walletAccountService = walletAccountService;
    }

    @Autowired
    public void setOperationTimeLineService(OperationTimeLineService operationTimeLineService) {
        this.operationTimeLineService = operationTimeLineService;
    }

    @Autowired
    public void setWalletAccountBalanceService(WalletAccountBalanceService walletAccountBalanceService) {
        this.walletAccountBalanceService = walletAccountBalanceService;
    }
}
