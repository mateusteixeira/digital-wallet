package br.com.dwallet.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "OperationError")
@CompoundIndex(name = "id_user", def = "{'id' : 1, 'idUser' : 1}")
public class OperationError {

    @Id
    private String id;
    private String idUser;
    private String idWalletAccount;
    private String error;
    private LocalDateTime operationAt;
    private String type;
}
