package br.com.dwallet.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("document")
    private String document;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("lastUpdateAt")
    private LocalDateTime lastUpdateAt;
}
