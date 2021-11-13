package br.com.dwallet.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document(collection = "WalletAccount")
@CompoundIndex(name = "id_accountNumber", def = "{'id' : 1, 'accountNumber': 1}")
@CompoundIndex(name = "id_user", def = "{'id' : 1, 'user.id': 1}")
public class WalletAccount {

    @Id
    private String id;
    private String accountName;
    private Long accountNumber;
    private BigDecimal balance;
    @DBRef
    private User user;
}
