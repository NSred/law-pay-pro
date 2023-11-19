package pcc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pcc.DTO.PCCRequestDTO;
import pcc.DTO.PCCResponseDTO;
import pcc.Model.Bank;
import pcc.Repository.BankRepository;

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

    public void sendToIssuerBank(PCCRequestDTO pccRequestDTO, String bankUrl) {
        //Objekat ceo prosledjujem PCCRequestDTO dalje na sledecu aplikaciju
        Object a = webClient.post()
                .uri(bankUrl+"/bank/payIssuer")
                .bodyValue(pccRequestDTO)
                .retrieve()
                .toBodilessEntity()
                .subscribe();
    }
    public void sendToAcquirerBank(PCCResponseDTO pccResponseDTO,String bankUrl) {
        //Objekat ceo prosledjujem PCCRequestDTO dalje na sledecu aplikaciju
        Object a = webClient.post()
                .uri(bankUrl+"/bank/payAcquirer")
                .bodyValue(pccResponseDTO)
                .retrieve()
                .toBodilessEntity()
                .subscribe();
    }

    public String getBankUrlId(String bankId) {
        Bank bank = this.bankRepository.getById(Long.parseLong(bankId));
        return bank.getUrl();
    }
}
