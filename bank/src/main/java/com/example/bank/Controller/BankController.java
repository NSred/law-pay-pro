package com.example.bank.Controller;

import com.example.bank.DTO.PCCRequestDTO;
import com.example.bank.DTO.PCCResponseDTO;
import com.example.bank.DTO.PSPResponseDTO;
import com.example.bank.Model.Card;
import com.example.bank.Model.Enum.Url;
import com.example.bank.Model.Transaction;
import com.example.bank.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bank.DTO.CardTransactionRequestDTO;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;
    @Autowired
    private CardService cardService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PspService pspService;

    @PostMapping("/pay")
    public ResponseEntity<PSPResponseDTO> transferMoney(@RequestBody CardTransactionRequestDTO cardTransactionRequestDTO) {
        try {
            Transaction transaction = transactionService.getTransactionbyPaymentId(cardTransactionRequestDTO.getPaymentId());
            if (bankService.isSameBank(cardTransactionRequestDTO.getPan())) {
                Card card = cardService.isCardInfoCorrect(cardTransactionRequestDTO);

                //How to gather merchantId
                if(accountService.withdrawMoney(card.getAccount().getAccountId(), transaction.getAmount())){
                    accountService.depositMoney(transaction.getMerchant().getId(),transaction.getAmount());
                    PSPResponseDTO response = bankService.sendBackToPSP(cardTransactionRequestDTO,card.getAccount().getAccountId(), transaction);
                    return ResponseEntity.ok(response);
                }else {
                    PSPResponseDTO response = pspService.response(Url.FAILED);
                    return ResponseEntity.ok().body(response);
                }
            } else {
                PCCResponseDTO pccResponseDTO = bankService.sendToPCC(cardTransactionRequestDTO, transaction);
                if(accountService.depositMoney(
                        accountService.getAccountId(pccResponseDTO.getAcquirerAccountNumber())
                        ,pccResponseDTO.getAmount())) {

                    PSPResponseDTO response = bankService.sendBackToPSPPCC(pccResponseDTO);
                    return ResponseEntity.ok(response);
                }
                PSPResponseDTO response = pspService.response(Url.FAILED);
                return ResponseEntity.ok(response);
            }
        } catch (RuntimeException e) {
            PSPResponseDTO response = pspService.response(Url.FAILED);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            PSPResponseDTO response = pspService.response(Url.ERROR);
            return ResponseEntity.ok(response);
        }
    }
    @PostMapping("/payIssuer")
    public ResponseEntity<PCCResponseDTO> takeMoneyIssuer(@RequestBody PCCRequestDTO pccRequestDTO  ) {
        try {
            if (bankService.isSameBank(pccRequestDTO.getPan())) {
                Card card = cardService.isCardInfoCorrectPCC(pccRequestDTO);
                //Maybe reserve money, so there must be reserve money
                if(accountService.withdrawMoney(card.getAccount().getAccountId(), pccRequestDTO.getAmount())){
                    //generisi issuer id i timestamp i upisi u bazu transaksicja i vrati nazad PCC
                    System.out.println("Money from issuer taken");
                    PCCResponseDTO pccResponseDTO=bankService.sendBackToPCC(pccRequestDTO,card.getAccount().getAccountNumber(),card.getAccount().getAccountId());

                    return ResponseEntity.ok(pccResponseDTO);
                }else {
                    throw new RuntimeException();
                    //return ResponseEntity.badRequest().body(new PCCResponseDTO());
                }
            } else {
                return ResponseEntity.ok(new PCCResponseDTO());
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PCCResponseDTO());
        }
    }
}