package com.bruno.microservices.card.services;

import com.bruno.microservices.card.dto.CardDTO;
import com.bruno.microservices.card.entities.Card;
import com.bruno.microservices.card.event.AccountEvent;
import com.bruno.microservices.card.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<Card> findAllCards() {
        return cardRepository.findAll();
    }

    @KafkaListener(topics = "notificationTopic")
    public void createNewCard(AccountEvent accountEvent) throws IOException {
        Card entity = Card.builder()
                .cardNumber(cardNumberGenerate())
                .cvc(cvcNumberGenerate())
                .cardPassword("123456")
                .cardLimit(10000.0)
                .accountID(accountEvent.getAccountID())
                .clientName(accountEvent.getClientName())
                .build();
        cardRepository.save(entity);
    }

    public Card createNewCard(CardDTO cardDTO, UUID accountID) {

        Card entity = Card.builder()
                .cardNumber(cardNumberGenerate())
                .cvc(cvcNumberGenerate())
                .cardPassword(cardDTO.getCardPassword())
                .cardLimit(10000.0)
                .accountID(cardDTO.getAccountID())
                .build();
        return cardRepository.save(entity);

    }

    public Card findCardById(UUID cardID) {
        return cardRepository.findById(cardID).get();
    }

    public Card updateCard(CardDTO cardDTO, UUID cardID) {

        Card entity = cardRepository.getOne(cardID);
        entity.setCardPassword(cardDTO.getCardPassword());
        entity.setCardLimit(cardDTO.getCardLimit());
        return cardRepository.save(entity);

    }

    public void deleteCardById(UUID cardID) {
        cardRepository.deleteById(cardID);
    }

    private String cardNumberGenerate() {
        Random rand = new Random();
        String cardNumber = "0462";
        for (int i = 0; i < 12; i++) {
            int n = rand.nextInt(10) + 0;
            cardNumber += Integer.toString(n);
        }
        return cardNumber;
    }

    private String cvcNumberGenerate() {
        Random rand = new Random();
        String cvcNumber = "";
        for (int i = 0; i < 3; i++) {
            int n = rand.nextInt(10) + 0;
            cvcNumber += Integer.toString(n);
        }
        return cvcNumber;
    }
}
