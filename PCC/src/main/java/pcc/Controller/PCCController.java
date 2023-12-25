package pcc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcc.DTO.PCCRequestDTO;
import pcc.DTO.PCCRequestQRDTO;
import pcc.DTO.PCCResponseDTO;
import pcc.DTO.PCCResponseQRDTO;
import pcc.Service.BankService;

@RestController
@RequestMapping("/pcc")
public class PCCController {
    @Autowired
    private BankService bankService;
    @PostMapping("/toIssuerBank")
    public ResponseEntity<PCCResponseDTO> redirectToIssuerBank(@RequestBody PCCRequestDTO pccRequestDTO) {
        try {
            String bankUrl = bankService.getBankUrl(pccRequestDTO.getPan());
            PCCResponseDTO pccResponseDTO = bankService.sendToIssuerBank(pccRequestDTO,bankUrl);
            System.out.println("Recieved from issuer bank response");
            //return bez url banke
            //bankService.sendToAcquirerBank(pccResponseDTO);
          return ResponseEntity.ok(pccResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PCCResponseDTO());
        }
    }
    @PostMapping("/toIssuerBankQR")
    public ResponseEntity<PCCResponseQRDTO> redirectToIssuerBank(@RequestBody PCCRequestQRDTO pccRequestQRDTO) {
        try {
            System.out.println("Stigoh ovde");
            String bankUrl = "http://localhost:8061";
            PCCResponseQRDTO pccResponseQRDTO = bankService.sendToIssuerBankQR(pccRequestQRDTO,bankUrl);
            System.out.println("Recieved from issuer bank response");
            //return bez url banke
            //bankService.sendToAcquirerBank(pccResponseDTO);
            return ResponseEntity.ok(pccResponseQRDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PCCResponseQRDTO());
        }
    }
}
