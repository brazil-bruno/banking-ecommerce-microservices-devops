package com.bruno.microservices.card.services;

import com.bruno.microservices.card.dto.CardDTO;
import com.bruno.microservices.card.entities.Account;
import com.bruno.microservices.card.entities.Card;
import com.bruno.microservices.card.feignclients.AccountFeignClient;
import com.bruno.microservices.card.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final AccountFeignClient accountFeignClient;

    public List<Card> findAllCards() {
        return cardRepository.findAll();
    }

    public Card createNewCard(CardDTO cardDTO, String accountID) {
        Account account = accountFeignClient.findAccountById(accountID);

        Card entity = Card.builder()
                .cardID(UUID.randomUUID().toString())
                .cardNumber(cardNumberGenerate())
                .cvc(cvcNumberGenerate())
                .cardPassword(cardDTO.getCardPassword())
                .cardLimit(0.0)
                .accountID(account.getAccountID())
                .build();
        return cardRepository.save(entity);

    }

    public Card findCardById(String cardID) {
        return cardRepository.findById(cardID).get();
    }

    public Card updateCard(CardDTO cardDTO, String cardID) {

        Card entity = cardRepository.getOne(cardID);
        entity.setCardPassword(cardDTO.getCardPassword());
        return cardRepository.save(entity);

    }

    public void deleteCardById(String cardID) {
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
