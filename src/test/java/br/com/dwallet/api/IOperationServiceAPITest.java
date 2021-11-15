package br.com.dwallet.api;

import br.com.dwallet.model.dto.BillPaymentDTO;
import br.com.dwallet.model.dto.DepositDTO;
import br.com.dwallet.model.dto.TransferDTO;
import br.com.dwallet.model.dto.WithDrawDTO;
import br.com.dwallet.service.operation.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class IOperationServiceAPITest {

    private static final String ID_USER = "q1w2e3r4";
    private static final String ID_WALLET_ACCOUNT = "AqSwDe";

    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationAPI operationAPI;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_verify_transfer() {
        TransferDTO transferDTO = TransferDTO.builder().build();
        ResponseEntity<Object> responseEntity = operationAPI.transfer(transferDTO, ID_USER, ID_WALLET_ACCOUNT);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(operationService).doTransfer(any(), anyString(), anyString());
    }

    @Test
    public void should_verify_deposit() {
        DepositDTO depositDTO = DepositDTO.builder().build();
        ResponseEntity<Object> responseEntity = operationAPI.deposit(depositDTO, ID_USER, ID_WALLET_ACCOUNT);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(operationService).doDeposit(any(), anyString(), anyString());
    }

    @Test
    public void should_verify_withdraw() {
        WithDrawDTO withDrawDTO = WithDrawDTO.builder().build();
        ResponseEntity<Object> responseEntity = operationAPI.withDraw(withDrawDTO, ID_USER, ID_WALLET_ACCOUNT);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(operationService).doWithDraw(any(), anyString(), anyString());
    }

    @Test
    public void should_verify_billpayment() {
        BillPaymentDTO billPaymentDTO = BillPaymentDTO.builder().build();
        ResponseEntity<Object> responseEntity = operationAPI.billPayment(billPaymentDTO, ID_USER, ID_WALLET_ACCOUNT);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(operationService).payBill(any(), anyString(), anyString());
    }

}