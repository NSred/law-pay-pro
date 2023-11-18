package rs.wepublishlaws.paymentserviceprovider.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.wepublishlaws.paymentserviceprovider.dto.RegistrationDTO;
import rs.wepublishlaws.paymentserviceprovider.service.MerchantService;


@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        if (merchantService.existsMerchantByMerchantId(registrationDTO.getMerchantId())) {
            return ResponseEntity.badRequest().body("Merchant id is already taken!");
        }
        String apiKey = merchantService.generateApiKey(
                registrationDTO.getMerchantId(),
                registrationDTO.getMerchantPassword()
        );
        return ResponseEntity.ok(apiKey);
    }

}
