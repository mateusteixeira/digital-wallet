package br.com.dwallet.service.operation;

import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.service.WalletAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Slf4j
public abstract class AbstractOutComingOperationService {

    private WalletAccountService walletAccountService;

    private OperationTimeLineService operationTimeLineService;

    private WalletAccountBalanceService walletAccountBalanceService;

    protected void doProcess(BigDecimal amount, String idUser, String idWalletAccount, String type) {
        log.info("Doing {} for idUser {} and idWalletAccount {} with amout {}", type, idUser, idWalletAccount, amount);
        WalletAccount walletAccount = walletAccountService.getWalletAccountByIdAndIdUser(idWalletAccount, idUser);
        WalletAccount walletAccountUpdated = walletAccountBalanceService.subtractAmountToWalletAccount(walletAccount, amount);
        walletAccountService.saveWalletAccount(walletAccountUpdated);
        operationTimeLineService.createOperationTimeLineForOutComing(walletAccountUpdated, amount, type);
        log.info("Done {} for idUser {} and idWalletAccount {} with amout {}", type, idUser, idWalletAccount, amount);
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
