package com.bruno.microservices.card.controllers;

import com.bruno.microservices.card.dto.CardDTO;
import com.bruno.microservices.card.entities.Card;
import com.bruno.microservices.card.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Card> findAllCards() {
        return cardService.findAllCards();
    }

    @PostMapping("/{accountID}")
    @ResponseStatus(HttpStatus.CREATED)
    public Card createNewCard(@RequestBody CardDTO cardDTO, @PathVariable UUID accountID) {
        return cardService.createNewCard(cardDTO, accountID);
    }

    @GetMapping("/{cardID}")
    @ResponseStatus(HttpStatus.OK)
    public Card findCardById(@PathVariable UUID cardID) {
        return cardService.findCardById(cardID);
    }

    @PutMapping("/{cardID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Card updateCard(@RequestBody CardDTO cardDTO, @PathVariable UUID cardID) {
        return cardService.updateCard(cardDTO, cardID);
    }

    @DeleteMapping("/{cardID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardById(@PathVariable UUID cardID) {
        cardService.deleteCardById(cardID);
    }
}
