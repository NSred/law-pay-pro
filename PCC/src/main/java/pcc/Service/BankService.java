package pcc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pcc.DTO.PCCRequestDTO;
import pcc.DTO.PCCRequestQRDTO;
import pcc.DTO.PCCResponseDTO;
import pcc.DTO.PCCResponseQRDTO;
import pcc.Model.Bank;
import pcc.Repository.BankRepository;
import reactor.core.publisher.Mono;

@Service
public class BankService {

    private final BankRepository bankRepository;
    private final WebClient webClient;


    @Autowired
    public BankService(BankRepository bankRepository, WebClient.Builder webClientBuilder) {
        this.bankRepository = bankRepository;
        this.webClient = webClientBuilder.build();
    }

    public String getBankUrl(String pan){
        String bankId = pan.substring(0, 6);
        Bank bank = this.bankRepository.getById(Long.parseLong(bankId));
        return bank.getUrl();

    }

    public PCCResponseDTO sendToIssuerBank(PCCRequestDTO pccRequestDTO, String bankUrl) {
        Mono<PCCResponseDTO> responseMono = webClient.post()
                .uri(bankUrl + "/bank/payIssuer")
                .bodyValue(pccRequestDTO)
                .retrieve()
                .bodyToMono(PCCResponseDTO.class);

        // Block and get the result
        return responseMono.block();
    }
    public PCCResponseQRDTO sendToIssuerBankQR(PCCRequestQRDTO pccRequestQRDTO, String bankUrl) {
        Mono<PCCResponseQRDTO> responseMono = webClient.post()
                .uri(bankUrl + "/bank/payIssuerQR")
                .bodyValue(pccRequestQRDTO)
                .retrieve()
                .bodyToMono(PCCResponseQRDTO.class);

        // Block and get the result
        return responseMono.block();
    }


    public String getBankUrlId(String bankId) {
        Bank bank = this.bankRepository.getById(Long.parseLong(bankId));
        return bank.getUrl();
    }
}
