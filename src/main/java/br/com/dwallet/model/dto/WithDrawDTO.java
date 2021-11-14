package br.com.dwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class WithDrawDTO implements Serializable {

    @JsonProperty("amount")
    private BigDecimal amount;
}
