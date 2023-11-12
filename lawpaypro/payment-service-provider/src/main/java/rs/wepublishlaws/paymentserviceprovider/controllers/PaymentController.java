package rs.wepublishlaws.paymentserviceprovider.controllers;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.wepublishlaws.buildingblocks.IActiveMqProducer;
import rs.wepublishlaws.shared.messages.PaymentMessage;

@RestController
@Data
public class PaymentController {
    private final IActiveMqProducer jmsTemplate;
    @PostMapping(value = "")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request){
        jmsTemplate.sendMessage("TESTIRANJE",
                new PaymentMessage(request.merchantId, request.merchantPassword,
                        request.amount, request.merchantOrderId, request.merchantTimestamp,
                        request.successUrl, request.failedUrl, request.errorUrl
                ));
        return ResponseEntity.ok().build();
    }
}
