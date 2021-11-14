package br.com.dwallet.api;

import br.com.dwallet.model.dto.BillPaymentDTO;
import br.com.dwallet.model.dto.DepositDTO;
import br.com.dwallet.model.dto.TransferDTO;
import br.com.dwallet.model.dto.WithDrawDTO;
import br.com.dwallet.service.operation.OperationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{idUser}/operation/{idWalletAccount}")
public class OperationAPI {

    private final OperationService operationService;

    public OperationAPI(OperationService operationService) {
        this.operationService = operationService;
    }

    @PutMapping("/transfer")
    public void transfer(@RequestBody TransferDTO transferDTO, @PathVariable(name = "idUser") String idUser, @PathVariable(name = "idWalletAccount") String idWalletAccount) {
        operationService.doTransfer(transferDTO, idUser, idWalletAccount);
    }

    @PutMapping("/withdraw")
    public void withDraw(@RequestBody WithDrawDTO withDrawDTO, @PathVariable(name = "idUser") String idUser, @PathVariable(name = "idWalletAccount") String idWalletAccount) {
        operationService.doWithDraw(withDrawDTO, idUser, idWalletAccount);
    }

    @PutMapping("/deposit")
    public void deposit(@RequestBody DepositDTO depositDTO, @PathVariable(name = "idUser") String idUser, @PathVariable(name = "idWalletAccount") String idWalletAccount) {
        operationService.doDeposit(depositDTO, idUser, idWalletAccount);
    }

    @PutMapping("/billpayment")
    public void billPayment(@RequestBody BillPaymentDTO billPaymentDTO, @PathVariable(name = "idUser") String idUser, @PathVariable(name = "idWalletAccount") String idWalletAccount) {
        operationService.payBill(billPaymentDTO, idUser, idWalletAccount);
    }

}
