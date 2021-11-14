package br.com.dwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class LifeTimeDTO implements Serializable {

    @JsonProperty("operationQuantity")
    private int operationQuantity;

    @JsonProperty("operations")
    private List<OperationLifeTimeDTO> operationLifeTimeDTOS;

}
