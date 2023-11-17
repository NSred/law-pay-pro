package com.example.bank.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {
/*
    @Autowired
    private CardService cardService;

    @Autowired
    private AccountService accountService;
*/
    public boolean isSameBank(String pan) {
        // Implement logic to check if the first six digits of the PAN match the bank number
        // You may need to retrieve the bank number from a configuration or database
        String bankNumber = "1234 12"; // Replace with the actual bank number
        return pan.startsWith(bankNumber);
    }


/*
    public void transferMoney(CardTransactionRequest cardTransactionRequest) {
        // Implement logic to transfer money from one account to another based on the card details
        // You may need to retrieve account details using the card information
        cardService.processCardTransaction(cardTransactionRequest);
    }*/
}
