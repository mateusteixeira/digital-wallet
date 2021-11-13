package br.com.dwallet.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "User")
@CompoundIndex(name = "id_document", def = "{'id' : 1, 'document': 1}")
public class User {


    @Id
    private String id;
    private String name;
    private String document;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;

}
