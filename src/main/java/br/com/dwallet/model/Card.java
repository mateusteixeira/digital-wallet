package br.com.dwallet.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "Card")
@CompoundIndex(name = "id_number", def = "{'id' : 1, 'number': 1}")
@CompoundIndex(name = "id_user", def = "{'id' : 1, 'user.id': 1}")
public class Card {

    @Id
    private String id;
    private String brand;
    private String number;
    @DBRef
    private User user;

}
