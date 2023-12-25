package com.example.bank.Service;

import com.example.bank.DTO.*;
import com.example.bank.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.example.bank.Model.Account;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private CardService cardService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;



    @Value("${bank.id}")
    private String bankId;
    @Value("${bank.success-url}")
    private String successUrl;

    private final WebClient webClient;
    public BankService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public boolean isSameBank(String pan) {
        return pan.startsWith(bankId);
    }
    public boolean isSameBankQR(String accountNumber) {
        Optional<Account> acc = accountService.getAccountbyAccountNumber(accountNumber);
        return acc.isPresent();
    }
    public IdTimestampDTO generateOrderId(){
        long order_id = (long) (Math.random() * 10000000000L);
        LocalDateTime timestamp = LocalDateTime.now();

        return new IdTimestampDTO(order_id, timestamp);
    }

    public PCCResponseDTO sendToPCC(CardTransactionRequestDTO cardTransactionRequestDTO, Transaction transaction) {
        IdTimestampDTO acquirerOrderIdTS = generateOrderId();
        //From where do I get merhcantId, merchant OrderId and timestamp paymentId
        String accNumber = accountService.getAccountNumberMerchant(transaction.getMerchant().getId());
        PCCRequestDTO pccRequestDTO = new PCCRequestDTO(
                cardTransactionRequestDTO.getPan(),
                cardTransactionRequestDTO.getSecurityCode(),
                cardTransactionRequestDTO.getCardHolderName(),
                cardTransactionRequestDTO.getExpirationDate(),
                transaction.getAmount(),
                acquirerOrderIdTS.getId(),
                acquirerOrderIdTS.getTimestamp(),
                bankId,
                transaction.getMerchantOrderId(),
                transaction.getMerchantTimestamp(),
                transaction.getPaymentId(),
                accNumber
                );
        transactionService.insertTransactionAcquirer(pccRequestDTO);
        Mono<PCCResponseDTO> responseMono = webClient.post()
                .uri("http://localhost:8062/pcc/toIssuerBank")
                .bodyValue(pccRequestDTO)
                .retrieve()
                .bodyToMono(PCCResponseDTO.class);

        // Block and get the result
        return responseMono.block();
    }
    public PCCResponseQRDTO sendToPCCQR(QRCodeDTO qrCodeDTO, Transaction transaction, String accNumber, String paymentId) {
        IdTimestampDTO acquirerOrderIdTS = generateOrderId();
        //From where do I get merhcantId, merchant OrderId and timestamp paymentId
        String accNumberMerch = accountService.getAccountNumberMerchant(transaction.getMerchant().getId());
        PCCRequestQRDTO pccRequestQRDTO = new PCCRequestQRDTO(
                accNumber,
                transaction.getAmount(),
                acquirerOrderIdTS.getId(),
                acquirerOrderIdTS.getTimestamp(),
                bankId,
                transaction.getMerchantOrderId(),
                transaction.getMerchantTimestamp(),
                transaction.getPaymentId(),
                accNumberMerch
        );
        transactionService.insertTransactionAcquirerQR(pccRequestQRDTO);
        Mono<PCCResponseQRDTO> responseMono = webClient.post()
                .uri("http://localhost:8062/pcc/toIssuerBankQR")
                .bodyValue(pccRequestQRDTO)
                .retrieve()
                .bodyToMono(PCCResponseQRDTO.class);

        // Block and get the result
        return responseMono.block();
    }


    public PCCResponseDTO sendBackToPCC(PCCRequestDTO pccRequestDTO, String accountNumber, Long userId) {
        IdTimestampDTO issuerOrderIdTS = generateOrderId();
        PCCResponseDTO pccResponseDTO = new PCCResponseDTO(
                pccRequestDTO.getPan(),
                pccRequestDTO.getSecurityCode(),
                pccRequestDTO.getCardHolderName(),
                pccRequestDTO.getExpirationDate(),
                pccRequestDTO.getAmount(),
                pccRequestDTO.getAcquirerOrderId(),
                pccRequestDTO.getAcquirerTimestamp(),
                issuerOrderIdTS.getId(),
                issuerOrderIdTS.getTimestamp(),
                pccRequestDTO.getBankId(),
                pccRequestDTO.getMerchantOrderId(),
                pccRequestDTO.getMerchantTimestamp(),
                pccRequestDTO.getPaymentId(),
                pccRequestDTO.getAcquirerAccountNumber(),
                accountNumber
        );
        transactionService.insertTransactionIssuer(pccResponseDTO, userId);
        return pccResponseDTO;

    }
    public PCCResponseQRDTO sendBackToPCCQR(PCCRequestQRDTO pccRequestQRDTO, Long userId) {
        IdTimestampDTO issuerOrderIdTS = generateOrderId();
        PCCResponseQRDTO pccResponseQRDTO = new PCCResponseQRDTO(
                pccRequestQRDTO.getAmount(),
                pccRequestQRDTO.getAcquirerOrderId(),
                pccRequestQRDTO.getAcquirerTimestamp(),
                issuerOrderIdTS.getId(),
                issuerOrderIdTS.getTimestamp(),
                pccRequestQRDTO.getBankId(),
                pccRequestQRDTO.getMerchantOrderId(),
                pccRequestQRDTO.getMerchantTimestamp(),
                pccRequestQRDTO.getPaymentId(),
                pccRequestQRDTO.getAcquirerAccountNumber(),
                pccRequestQRDTO.getAccNumber()
        );
        transactionService.insertTransactionIssuerQR(pccResponseQRDTO, userId);
        return pccResponseQRDTO;

    }

    public PSPResponseDTO sendBackToPSPPCC(PCCResponseDTO pccResponseDTO) {
        transactionService.updateTransaction(pccResponseDTO);
        return new PSPResponseDTO(
                successUrl,
                pccResponseDTO.getAcquirerOrderId(),
                pccResponseDTO.getAcquirerTimestamp(),
                pccResponseDTO.getMerchantOrderId(),
                pccResponseDTO.getPaymentId()
        );
    }
    public PSPResponseDTO sendBackToPSPPCCQR(PCCResponseQRDTO pccResponseQRDTO) {
        transactionService.updateTransactionQR(pccResponseQRDTO);
        return new PSPResponseDTO(
                successUrl,
                pccResponseQRDTO.getAcquirerOrderId(),
                pccResponseQRDTO.getAcquirerTimestamp(),
                pccResponseQRDTO.getMerchantOrderId(),
                pccResponseQRDTO.getPaymentId()
        );
    }
    public PSPResponseDTO sendBackToPSP(CardTransactionRequestDTO cardTransactionRequestDTO, Long issuerId, Transaction transaction) {
        IdTimestampDTO acquirerOrderIdTS = generateOrderId();
        //From where do I get merhcantId, merchant OrderId and timestamp paymentId
        String accNumberMerch = accountService.getAccountNumberMerchant(transaction.getMerchant().getId());
        String accNumberIss = accountService.getAccountNumberIssuer(issuerId);
        PCCRequestDTO pccRequestDTO = new PCCRequestDTO(
                cardTransactionRequestDTO.getPan(),
                cardTransactionRequestDTO.getSecurityCode(),
                cardTransactionRequestDTO.getCardHolderName(),
                cardTransactionRequestDTO.getExpirationDate(),
                transaction.getAmount(),
                acquirerOrderIdTS.getId(),
                acquirerOrderIdTS.getTimestamp(),
                bankId,
                transaction.getMerchantOrderId(),
                transaction.getMerchantTimestamp(),
                transaction.getPaymentId(),
                accNumberMerch
        );
        transactionService.insertTransaction(pccRequestDTO,accNumberIss, issuerId);
        return new PSPResponseDTO(
                successUrl,
                acquirerOrderIdTS.getId(),
                acquirerOrderIdTS.getTimestamp(),
                transaction.getMerchantOrderId(),
                transaction.getPaymentId()
        );
    }
    public PSPResponseDTO sendBackToPSPQR(Long issuerId, Transaction transaction) {
        IdTimestampDTO acquirerOrderIdTS = generateOrderId();
        //From where do I get merhcantId, merchant OrderId and timestamp paymentId
        String accNumberMerch = accountService.getAccountNumberMerchant(transaction.getMerchant().getId());
        String accNumberIss = accountService.getAccountNumberIssuer(issuerId);
        PCCRequestDTO pccRequestDTO = new PCCRequestDTO(
                "",
                "",
                "",
                "",
                transaction.getAmount(),
                acquirerOrderIdTS.getId(),
                acquirerOrderIdTS.getTimestamp(),
                bankId,
                transaction.getMerchantOrderId(),
                transaction.getMerchantTimestamp(),
                transaction.getPaymentId(),
                accNumberMerch
        );
        transactionService.insertTransaction(pccRequestDTO,accNumberIss, issuerId);
        return new PSPResponseDTO(
                successUrl,
                acquirerOrderIdTS.getId(),
                acquirerOrderIdTS.getTimestamp(),
                transaction.getMerchantOrderId(),
                transaction.getPaymentId()
        );
    }
}
