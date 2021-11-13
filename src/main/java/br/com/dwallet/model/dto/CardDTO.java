package br.com.dwallet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CardDTO implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("number")
    private String number;

    @JsonProperty("idUser")
    private String idUser;

}
