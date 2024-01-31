package com.example.bank.Service;

import com.example.bank.DTO.CardTransactionRequestDTO;
import com.example.bank.DTO.PCCRequestDTO;
import com.example.bank.Model.Card;
import com.example.bank.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final EncryptionService encryptionService;
    @Value("${app.encryption.secret-key}")
    private String encodedKey;

    @Autowired
    public CardService(CardRepository cardRepository, EncryptionService encryptionService) {
        this.cardRepository = cardRepository;
        this.encryptionService = encryptionService;
    }

    public void saveCard(Card card) throws Exception {
        String encryptedPan = encryptionService.encrypt(card.getPan(), encodedKey);
        String encryptedSecurityCode = encryptionService.encrypt(card.getSecurityCode(), encodedKey);
        card.setPan(encryptedPan);
        card.setSecurityCode(encryptedSecurityCode);
        cardRepository.save(card);
    }

    public Card isCardInfoCorrect(CardTransactionRequestDTO cardTransactionRequestDTO) throws Exception {
        // Mozda heshovati security code i vratiti samo poslednja 4 broja PAN-a
        Card card = cardRepository.findByCardHolderNameAndExpirationDate(
                cardTransactionRequestDTO.getCardHolderName(), cardTransactionRequestDTO.getExpirationDate());
        String decryptedPan = encryptionService.decrypt(card.getPan(), encodedKey);
        String decryptedSecurityCode = encryptionService.decrypt(card.getSecurityCode(), encodedKey);
        if (decryptedPan.equals(cardTransactionRequestDTO.getPan()) && decryptedSecurityCode.equals(cardTransactionRequestDTO.getSecurityCode())) {
            return card;
        } else {
            throw new RuntimeException("Not correct card");
        }
    }
    public Card isCardInfoCorrectPCC(PCCRequestDTO pccRequestDTO) throws Exception {
        // Mozda heshovati security code i vratiti samo poslednja 4 broja PAN-a
        Card card = cardRepository.findByCardHolderNameAndExpirationDate(
                pccRequestDTO.getCardHolderName(), pccRequestDTO.getExpirationDate());
        String decryptedPan = encryptionService.decrypt(card.getPan(), encodedKey);
        String decryptedSecurityCode = encryptionService.decrypt(card.getSecurityCode(), encodedKey);
        if (decryptedPan.equals(pccRequestDTO.getPan()) && decryptedSecurityCode.equals(pccRequestDTO.getSecurityCode())) {
            return card;
        } else {
            throw new RuntimeException("Not correct card");
        }
    }
}
