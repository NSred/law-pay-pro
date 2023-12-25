package com.example.bank.Controller;

import com.example.bank.DTO.*;
import com.example.bank.Model.Card;
import com.example.bank.Model.Enum.Url;
import com.example.bank.Model.Transaction;
import com.example.bank.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private QRService qrService;

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
    @PostMapping("/payQR")
    public ResponseEntity<PSPResponseDTO> transferMoneyQR(@RequestBody QRCodeRequestDTO qrCodeRequestDTO) {
        try {
            Transaction transaction = transactionService.getTransactionbyPaymentId(qrCodeRequestDTO.getPaymentId());
            String qrCode = qrService.qrCodeToString(qrCodeRequestDTO.getQrCode());
            if(qrService.validateQrCode(qrCode)) {
                QRCodeDTO qrCodeDTO = qrService.toQRCodeDTO(qrCode);
                if (bankService.isSameBankQR(qrCodeRequestDTO.getAccountNumber())) {


                    //How to gather merchantId
                    if (accountService.withdrawMoneyQR(qrCodeRequestDTO.getAccountNumber(), transaction.getAmount())) {
                        accountService.depositMoneyQR(qrCodeDTO.getR(), transaction.getAmount());
                        PSPResponseDTO response = bankService.sendBackToPSPQR(accountService.getAccountId(qrCodeRequestDTO.getAccountNumber()), transaction);
                        return ResponseEntity.ok(response);
                    } else {
                        PSPResponseDTO response = pspService.response(Url.FAILED);
                        return ResponseEntity.ok().body(response);
                    }
                } else {
                    System.out.println("Eo me ovde");
                    PCCResponseQRDTO pccResponseQRDTO = bankService.sendToPCCQR(qrCodeDTO, transaction, qrCodeRequestDTO.getAccountNumber(), qrCodeRequestDTO.getPaymentId());
                    if (accountService.depositMoneyQR(
                            pccResponseQRDTO.getAcquirerAccountNumber()
                            , pccResponseQRDTO.getAmount())) {

                        PSPResponseDTO response = bankService.sendBackToPSPPCCQR(pccResponseQRDTO);
                        return ResponseEntity.ok(response);
                    }
                    PSPResponseDTO response = pspService.response(Url.FAILED);
                    return ResponseEntity.ok(response);
                }
            }else{
                PSPResponseDTO response = pspService.response(Url.ERROR);
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

    @PostMapping("/payIssuerQR")
    public ResponseEntity<PCCResponseQRDTO> takeMoneyIssuer(@RequestBody PCCRequestQRDTO pccRequestQRDTO  ) {
        try {
            if (bankService.isSameBankQR(pccRequestQRDTO.getAccNumber())) {
                //Maybe reserve money, so there must be reserve money
                if(accountService.withdrawMoneyQR(pccRequestQRDTO.getAccNumber(), pccRequestQRDTO.getAmount())){
                    //generisi issuer id i timestamp i upisi u bazu transaksicja i vrati nazad PCC
                    System.out.println("Money from issuer taken");
                    PCCResponseQRDTO pccResponseQRDTO=bankService.sendBackToPCCQR(pccRequestQRDTO,accountService.getAccountId(pccRequestQRDTO.getAccNumber()));

                    return ResponseEntity.ok(pccResponseQRDTO);
                }else {
                    throw new RuntimeException();
                    //return ResponseEntity.badRequest().body(new PCCResponseDTO());
                }
            } else {
                return ResponseEntity.ok(new PCCResponseQRDTO());
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PCCResponseQRDTO());
        }
    }
}