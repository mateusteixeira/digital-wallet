package br.com.dwallet.service.operation;

import br.com.dwallet.model.dto.BillPaymentDTO;
import br.com.dwallet.model.dto.DepositDTO;
import br.com.dwallet.model.dto.TransferDTO;
import br.com.dwallet.model.dto.WithDrawDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;

class IOperationServiceServiceTest {

    private static final String ID_USER = "A1q1";
    private static final String ID_WALLET_ACCOUNT = "A1q132";

    @Mock
    private DepositService depositService;

    @Mock
    private TransferService transferService;

    @Mock
    private WithDrawService withDrawService;

    @Mock
    private BillPaymentService billPaymentService;

    @InjectMocks
    private OperationService operationService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_verify_deposit() {
        DepositDTO depositDTO = DepositDTO.builder().amount(new BigDecimal("12")).build();
        operationService.doDeposit(depositDTO, ID_USER, ID_WALLET_ACCOUNT);
        verify(depositService).doDeposit(depositDTO.getAmount(), ID_USER, ID_WALLET_ACCOUNT);
    }

    @Test
    public void should_verify_with_draw() {
        WithDrawDTO withDrawDTO = WithDrawDTO.builder().amount(new BigDecimal("12")).build();
        operationService.doWithDraw(withDrawDTO, ID_USER, ID_WALLET_ACCOUNT);
        verify(withDrawService).doWithDraw(withDrawDTO.getAmount(), ID_USER, ID_WALLET_ACCOUNT);
    }

    @Test
    public void should_verify_transfer() {
        TransferDTO transferDTO = TransferDTO.builder().build();
        operationService.doTransfer(transferDTO, ID_USER, ID_WALLET_ACCOUNT);
        verify(transferService).doTransfer(transferDTO, ID_USER, ID_WALLET_ACCOUNT);
    }

    @Test
    public void should_verify_bill_payment() {
        BillPaymentDTO billPaymentDTO = BillPaymentDTO.builder().value(new BigDecimal("12")).build();
        operationService.payBill(billPaymentDTO, ID_USER, ID_WALLET_ACCOUNT);
        verify(billPaymentService).payBill(billPaymentDTO.getValue(), ID_USER, ID_WALLET_ACCOUNT);
    }

}