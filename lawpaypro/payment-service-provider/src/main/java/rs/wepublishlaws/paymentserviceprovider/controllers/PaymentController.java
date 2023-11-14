package rs.wepublishlaws.paymentserviceprovider.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.wepublishlaws.buildingblocks.IProducer;
import rs.wepublishlaws.paymentserviceprovider.service.PaymentService;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.messages.PaymentResponse;
import rs.wepublishlaws.shared.queues.QueueConstants;

@RestController
@Data
public class PaymentController {
//    private final IProducer jmsTemplate;
    private final PaymentService paymentService;

    @Autowired
        public PaymentController(final PaymentService paymentService){
        this.paymentService = paymentService;
    }
    @PostMapping(value = "")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request){
        PaymentResponse response = paymentService.processPayment(request);

//        String destination = switch (request.service) {
//            case "qrcode" -> QueueConstants.QRCODE_SERVICE_QUEUE;
//            case "paypal" -> QueueConstants.PAYPAL_SERVICE_QUEUE;
//            case "crypto" -> QueueConstants.CRYPTO_SERVICE_QUEUE;
//            case "card" -> QueueConstants.CARD_SERVICE_QUEUE;
//            case "null" -> QueueConstants.CARD_SERVICE_QUEUE;
//            default -> "";
//        };
//        PaymentResponse response = jmsTemplate.sendAndReceive(destination,
//                new PaymentMessage(request.service)
//                /*new PaymentMessage(request.merchantId, request.merchantPassword,
//                        request.amount, request.merchantOrderId, request.merchantTimestamp,
//                        request.successUrl, request.failedUrl, request.errorUrl
//                )*/, PaymentResponse.class);
        return ResponseEntity.ok(response);
    }
}
