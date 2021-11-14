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
public class WalletAccountDTO implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("accountName")
    private String accountName;

    @JsonProperty("accountNumber")
    private Long accountNumber;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("idUser")
    private String idUser;

}
