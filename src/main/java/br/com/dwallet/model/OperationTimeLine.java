package br.com.dwallet.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "OperationTimeLine")
@CompoundIndex(name = "id_user", def = "{'id' : 1, 'user.id' : 1}")
@CompoundIndex(name = "id_wallet", def = "{'id' : 1, 'walletAccount.id' : 1}")
public class OperationTimeLine {

    @Id
    private String id;
    @DBRef
    private User user;
    @DBRef
    private WalletAccount walletAccount;
    private boolean incoming;
    private LocalDateTime operationAt;
    private BigDecimal value;
    private String type;
}
