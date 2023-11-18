package com.example.bank.Controller;

import com.example.bank.Model.Card;
import com.example.bank.Service.AccountService;
import com.example.bank.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bank.DTO.CardTransactionRequestDTO;
import com.example.bank.Service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;
    @Autowired
    private CardService cardService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/pay")
    public ResponseEntity<String> transferMoney(@RequestBody CardTransactionRequestDTO cardTransactionRequestDTO) {
        try {
            if (bankService.isSameBank(cardTransactionRequestDTO.getPan())) {
                Card card = cardService.isCardInfoCorrect(cardTransactionRequestDTO);
                //How to gather merchantId
                if(accountService.withdrawMoney(card.getAccount().getAccountId(), cardTransactionRequestDTO.getAmount())){
                    accountService.depositMoney(1L,cardTransactionRequestDTO.getAmount());
                    return ResponseEntity.ok("Transaction successfull");
                }else {
                    return ResponseEntity.badRequest().body("Not enough money");
                }
            } else {
                String a = bankService.sendToPCC(cardTransactionRequestDTO);
                System.out.println(a);
                return ResponseEntity.ok("Card is not from the same bank it is sent to PCC");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error transferring money: " + e.getMessage());
        }
    }
    @PostMapping("/payIssuer")
    public ResponseEntity<String> takeMoneyIssuer(@RequestBody CardTransactionRequestDTO cardTransactionRequestDTO) {
        try {
            if (bankService.isSameBank(cardTransactionRequestDTO.getPan())) {
                Card card = cardService.isCardInfoCorrect(cardTransactionRequestDTO);
                //Maybe reserve money, so there must be reserve money
                if(accountService.withdrawMoney(card.getAccount().getAccountId(), cardTransactionRequestDTO.getAmount())){
                    //generisi issuer id i timestamp i upisi u bazu transaksicja i vrati nazad PCC
                    return ResponseEntity.ok("Transaction successfull");
                }else {
                    return ResponseEntity.badRequest().body("Not enough money");
                }
            } else {
                return ResponseEntity.ok("Card is not from the same bank there was an error in PCC");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error transferring money: " + e.getMessage());
        }
    }
}