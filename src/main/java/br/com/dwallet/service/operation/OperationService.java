package br.com.dwallet.service.operation;

import br.com.dwallet.model.dto.BillPaymentDTO;
import br.com.dwallet.model.dto.DepositDTO;
import br.com.dwallet.model.dto.TransferDTO;
import br.com.dwallet.model.dto.WithDrawDTO;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    private final DepositService depositService;

    private final TransferService transferService;

    private final WithDrawService withDrawService;

    private final BillPaymentService billPaymentService;

    public OperationService(DepositService depositService, TransferService transferService, WithDrawService withDrawService, BillPaymentService billPaymentService) {
        this.depositService = depositService;
        this.transferService = transferService;
        this.withDrawService = withDrawService;
        this.billPaymentService = billPaymentService;
    }

    public void doTransfer(TransferDTO transferDTO, String idUser, String idWalletAccount) {
        transferService.doTransfer(transferDTO, idUser, idWalletAccount);
    }

    public void doWithDraw(WithDrawDTO withDrawDTO, String idUser, String idWalletAccount) {
        withDrawService.doWithDraw(withDrawDTO.getAmount(), idUser, idWalletAccount);
    }

    public void doDeposit(DepositDTO depositDTO, String idUser, String idWalletAccount) {
        depositService.doDeposit(depositDTO.getAmount(), idUser, idWalletAccount);
    }

    public void payBill(BillPaymentDTO billPaymentDTO, String idUser, String idWalletAccount) {
        billPaymentService.payBill(billPaymentDTO.getValue(), idUser, idWalletAccount);
    }

}
