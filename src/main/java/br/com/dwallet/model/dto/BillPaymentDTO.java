package br.com.dwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class BillPaymentDTO implements Serializable {

    @JsonProperty("value")
    private BigDecimal value;

    @JsonProperty("billName")
    private String billName;
}
