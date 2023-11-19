package com.example.bank.Service;

import com.example.bank.DTO.*;
import com.example.bank.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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
        /*
        Object a = webClient.post()
                .uri("http://localhost:8082/pcc/toAcquirerBank")
                .bodyValue(pccResponseDTO)
                .retrieve()
                .toBodilessEntity()
                .subscribe();

         */
        return pccResponseDTO;

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
}
