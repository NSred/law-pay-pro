package pcc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcc.DTO.PCCRequestDTO;
import pcc.Service.BankService;

@RestController
@RequestMapping("/pcc")
public class PCCController {
    @Autowired
    private BankService bankService;
    @PostMapping("/toIssuerBank")
    public ResponseEntity<String> transferMoney() {
        try {
            String bankUrl = bankService.getBankUrl("1234567");
            bankService.sendToIssuerBank(bankUrl);

          return ResponseEntity.ok("Vraceno iz druge aplikacije");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error transferring money: " + e.getMessage());
        }
    }
}
