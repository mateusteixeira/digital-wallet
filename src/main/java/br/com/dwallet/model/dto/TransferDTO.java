package br.com.dwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
