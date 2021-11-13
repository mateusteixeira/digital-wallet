package br.com.dwallet.translator;

import br.com.dwallet.model.Card;
import br.com.dwallet.model.dto.CardDTO;
import org.springframework.stereotype.Component;

@Component
public class CardTranslator {

    public CardDTO toDTO(Card card) {
        return CardDTO.builder()
                .id(card.getId())
                .brand(card.getBrand())
                .number(card.getNumber())
                .idUser(card.getUser().getId())
                .build();
    }

    public Card fromDTO(CardDTO cardDTO) {
        return Card.builder()
                .id(cardDTO.getId())
                .brand(cardDTO.getBrand())
                .number(cardDTO.getNumber())
                .build();
    }

}
