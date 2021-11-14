package br.com.dwallet.validator.operation;

import br.com.dwallet.exception.AmountBusinessException;
import br.com.dwallet.exception.TransferBusinessException;
import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.service.WalletAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public abstract class AbstractValidator {

    private WalletAccountService walletAccountService;

    protected void validateAmountBiggerThanZero(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AmountBusinessException(String.format("It is not possible do the operation with this amount. Amount: %s", amount));
        }
    }

    protected void validateWalletAccountNotSame(String idWalletAccountFrom, Long accountNumberTo) {
        WalletAccount walletAccount = walletAccountService.getWalletAccountByNumber(accountNumberTo);
        if (walletAccount.getId().equals(idWalletAccountFrom)) {
            throw new TransferBusinessException("It is not possible transfer to same account");
        }
    }

    protected void validateUsersNotSame(String idUserTo, String idUserFrom) {
        if (idUserTo.equals(idUserFrom)) {
            throw new TransferBusinessException("It is not possible transfer to same user");
        }
    }

    @Autowired
    public void setWalletAccountService(WalletAccountService walletAccountService) {
        this.walletAccountService = walletAccountService;
    }
}
