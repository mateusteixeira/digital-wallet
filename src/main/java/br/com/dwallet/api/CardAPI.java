package br.com.dwallet.api;

import br.com.dwallet.model.dto.CardDTO;
import br.com.dwallet.service.CardService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user/{idUser}/card")
public class CardAPI {

    private final CardService cardService;

    public CardAPI(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO cardDTO, @PathVariable(name = "idUser") String idUser) {
        cardDTO.setIdUser(idUser);
        CardDTO savedCardDTO = this.cardService.createCard(cardDTO);
        URI location = getUriToHeader(savedCardDTO);
        return ResponseEntity.created(location).body(savedCardDTO);
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> getCards(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int pageSize,
                                                  @RequestParam(defaultValue = "id,desc") String[] sort) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sort));
        return ResponseEntity.ok(cardService.getAllCards(paging));
    }

    @GetMapping("{id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable(name = "id") String idCard) {
        return ResponseEntity.ok(cardService.getCardById(idCard));
    }

    @PutMapping("{id}")
    public void updateCard(@RequestBody CardDTO cardDTO, @PathVariable(name = "id") String idCard) {
        cardService.updateCard(cardDTO, idCard);
    }

    @DeleteMapping("{id}")
    public void deleteCard(@PathVariable(name = "id") String idCard) {
        cardService.deleteCard(idCard);
    }

    @DeleteMapping
    public void deleteAllCards() {
        cardService.deleteAllCards();
    }

    private URI getUriToHeader(CardDTO cardDTO) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cardDTO.getId())
                .toUri();
    }


}
