package br.com.dwallet.service.operation;

import br.com.dwallet.model.OperationType;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.service.WalletAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public abstract class AbstractOutComingOperationService {

    private WalletAccountService walletAccountService;

    private OperationTimeLineService operationTimeLineService;

    private WalletAccountBalanceService walletAccountBalanceService;

    protected void doProcess(BigDecimal amount, String idUser, String idWalletAccount, String type) {
        WalletAccount walletAccount = walletAccountService.getWalletAccountByIdAndIdUser(idWalletAccount, idUser);
        walletAccountBalanceService.subtractAmountToWalletAccount(walletAccount, amount);
        walletAccountService.saveWalletAccount(walletAccount);
        operationTimeLineService.createOperationTimeLineForOutComing(walletAccount, amount, type);
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
