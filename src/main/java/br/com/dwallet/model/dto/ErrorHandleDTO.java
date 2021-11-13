package br.com.dwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorHandleDTO {

    @JsonProperty("message")
    private String message;
}
