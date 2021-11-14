package br.com.dwallet.queues.messages;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class AsyncOperationMessage implements Serializable {

    private String idUserFrom;
    private String idWalletAccountFrom;
    private String idUserTo;
    private Long accountNumberTo;
    private BigDecimal amount;
    private String type;

}
