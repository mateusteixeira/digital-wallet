package br.com.dwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class WalletAccountLifeTimeDTO implements Serializable {

    @JsonProperty("accountName")
    private String accountName;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("operationQuantity")
    private int operationQuantity;

    @JsonProperty("operations")
    private List<OperationLifeTimeDTO>  operationLifeTimeDTOS;
}
