package br.com.dwallet.api;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class OperationAPI {

    @PutMapping("/transfer")
    public void transfer() {

    }
    @PutMapping("/withdraw")
    public void withDraw() {

    }
    @PutMapping("/deposit")
    public void deposit() {

    }
    @PutMapping("/billpayment")
    public void billPayment() {

    }

}
