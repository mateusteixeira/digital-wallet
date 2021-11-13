package br.com.dwallet.validator;

import br.com.dwallet.exception.CardAlreadyExistsException;
import br.com.dwallet.model.Card;
import br.com.dwallet.model.CardRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CardValidator {

    private final CardRepository cardRepository;

    public CardValidator(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void validateCardExists(Card card) {
        Card existentCard = cardRepository.findByNumber(card.getNumber());
        if (Objects.nonNull(existentCard)) {
            throw new CardAlreadyExistsException(String.format("Card already exists for number %s", card.getNumber()));
        }
    }

}
