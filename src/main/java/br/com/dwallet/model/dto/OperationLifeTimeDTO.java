package br.com.dwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OperationLifeTimeDTO implements Serializable {

    @JsonProperty("accountName")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String accountName;

    @JsonProperty("incoming")
    private boolean incoming;

    @JsonProperty("operationAt")
    private LocalDateTime operationAt;

    @JsonProperty("value")
    private BigDecimal value;

    @JsonProperty("type")
    private String type;

}
