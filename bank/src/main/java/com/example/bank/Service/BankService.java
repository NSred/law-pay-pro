package com.example.bank.Service;

import com.example.bank.DTO.CardTransactionRequestDTO;
import com.example.bank.DTO.IdTimestampDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;

import java.time.LocalDateTime;

@Service
public class BankService {
/*
    @Autowired
    private CardService cardService;

    @Autowired
    private AccountService accountService;

*/
    private final WebClient webClient;
    public BankService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public boolean isSameBank(String pan) {
        //iz konfiguracije nekako?
        String bankNumber = "1234 12"; // Replace with the actual bank number
        return pan.startsWith(bankNumber);
    }
    public IdTimestampDTO generateAcquirerOrderId(){
        long acquirer_order_id = (long) (Math.random() * 10000000000L);
        LocalDateTime timestamp = LocalDateTime.now();

        return new IdTimestampDTO(acquirer_order_id, timestamp);
    }

    public String sendToPCC(CardTransactionRequestDTO cardTransactionRequestDTO) {
        IdTimestampDTO acquirerOrderIdTS = generateAcquirerOrderId();
        System.out.println(acquirerOrderIdTS.toString());
        //napravi DTO za slanje na PCC i pogoditi nekako PCC backend rutu
        Object a = webClient.post()
                .uri("http://localhost:8082/pcc/toIssuerBank")
                .bodyValue("")
                .retrieve()
                .toBodilessEntity()
                .subscribe();

        return  a.toString();
    }

}
