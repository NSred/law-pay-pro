package pcc.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcc.DTO.PCCRequestDTO;

@RestController
@RequestMapping("/pcc")
public class PCCController {
    @PostMapping("/toIssuerBank")
    public ResponseEntity<String> transferMoney() {
        try {
            System.out.println("pozdrav iz druge aplikacije");
          return ResponseEntity.ok("Vraceno iz druge aplikacije");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error transferring money: " + e.getMessage());
        }
    }
}
