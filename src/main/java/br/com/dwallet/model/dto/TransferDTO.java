package br.com.dwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class TransferDTO implements Serializable {

    @JsonProperty("userTo")
    private String idUserTo;

    @JsonProperty("accountNumberTo")
    private Long accountNumberTo;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("type")
    private String type;

}
