package br.com.dwallet.service;

import br.com.dwallet.exception.CardNotFoundException;
import br.com.dwallet.model.Card;
import br.com.dwallet.model.CardRepository;
import br.com.dwallet.model.User;
import br.com.dwallet.model.dto.CardDTO;
import br.com.dwallet.translator.CardTranslator;
import br.com.dwallet.validator.CardValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final UserService userService;

    private final CardValidator cardValidator;
    
    private final CardRepository cardRepository;
    
    private final CardTranslator cardTranslator;

    public CardService(UserService userService, CardValidator cardValidator, CardRepository cardRepository, CardTranslator cardTranslator) {
        this.userService = userService;
        this.cardValidator = cardValidator;
        this.cardRepository = cardRepository;
        this.cardTranslator = cardTranslator;
    }

    public CardDTO createCard(CardDTO cardDTO) {
        User user = userService.getUserOrThrowNotFoundException(cardDTO.getIdUser());
        Card card = cardTranslator.fromDTO(cardDTO);
        cardValidator.validateCardExists(card);
        card.setUser(user);
        return cardTranslator.toDTO(cardRepository.save(card));
    }

    public CardDTO getCardById(String idCard) {
        Card card = getCardOrThrowNotFoundException(idCard);
        return cardTranslator.toDTO(card);
    }

    private Card getCardOrThrowNotFoundException(String idCard) {
        Optional<Card> cardOp = cardRepository.findById(idCard);
        return cardOp.orElseThrow(() -> new CardNotFoundException(String.format("Card for id %s not found", idCard)));
    }

    public List<CardDTO> getAllCards(Pageable paging) {
        return cardRepository.findAll(paging).stream().map(cardTranslator::toDTO).collect(Collectors.toList());
    }

    public void updateCard(CardDTO cardDTO, String idCard) {
        Card card = getCardOrThrowNotFoundException(idCard);
        card.setBrand(cardDTO.getBrand());
        card.setNumber(card.getNumber());
        cardRepository.save(card);
    }

    public void deleteCard(String idCard) {
        Card card = getCardOrThrowNotFoundException(idCard);
        cardRepository.delete(card);
    }

    public void deleteAllCards() {
        cardRepository.deleteAll();
    }
}
