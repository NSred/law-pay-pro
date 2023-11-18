package com.example.bank.Service;

import com.example.bank.DTO.CardTransactionRequestDTO;
import com.example.bank.Model.Card;
import com.example.bank.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card isCardInfoCorrect(CardTransactionRequestDTO cardTransactionRequestDTO) {
        // Mozda heshovati security code i vratiti samo poslednja 4 broja PAN-a
        Card card = cardRepository.findByPanAndSecurityCodeAndCardHolderNameAndExpirationDate(
                cardTransactionRequestDTO.getPan(), cardTransactionRequestDTO.getSecurityCode(), cardTransactionRequestDTO.getCardHolderName(), cardTransactionRequestDTO.getExpirationDate());

        return card;
    }
}
