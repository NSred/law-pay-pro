package rs.wepublishlaws.paymentserviceprovider.controllers;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.wepublishlaws.buildingblocks.IProducer;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.messages.PaymentResponse;
import rs.wepublishlaws.shared.queues.QueueConstants;

@RestController
@Data
public class PaymentController {
    private final IProducer jmsTemplate;
    @PostMapping(value = "")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request){
        String destination = switch (request.service) {
            case "qrcode" -> QueueConstants.QRCODE_SERVICE_QUEUE;
            case "paypal" -> QueueConstants.PAYPAL_SERVICE_QUEUE;
            case "crypto" -> QueueConstants.CRYPTO_SERVICE_QUEUE;
            case "card" -> QueueConstants.CARD_SERVICE_QUEUE;
            default -> "";
        };
        PaymentResponse response = jmsTemplate.sendAndReceive(destination,
                new PaymentMessage(request.service)
                /*new PaymentMessage(request.merchantId, request.merchantPassword,
                        request.amount, request.merchantOrderId, request.merchantTimestamp,
                        request.successUrl, request.failedUrl, request.errorUrl
                )*/, PaymentResponse.class);
        return ResponseEntity.ok(response);
    }
}
