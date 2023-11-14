package rs.wepublishlaws.paymentserviceprovider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.paymentserviceprovider.controllers.PaymentRequest;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.messages.PaymentResponse;
import rs.wepublishlaws.shared.queues.QueueConstants;
import rs.wepublishlaws.buildingblocks.IProducer;

@Service
public class PaymentService {

    private final IProducer jmsTemplate;

    @Autowired
    public PaymentService(IProducer jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    public PaymentResponse processPayment(PaymentRequest request) {

        String destination = switch (request.service) {
            case "qrcode" -> QueueConstants.QRCODE_SERVICE_QUEUE;
            case "paypal" -> QueueConstants.PAYPAL_SERVICE_QUEUE;
            case "crypto" -> QueueConstants.CRYPTO_SERVICE_QUEUE;
            case "card" -> QueueConstants.CARD_SERVICE_QUEUE;
            case "null" -> QueueConstants.CARD_SERVICE_QUEUE;
            default -> "";
        };
        PaymentResponse response = jmsTemplate.sendAndReceive(destination,
                new PaymentMessage(request.service)
                /*new PaymentMessage(request.merchantId, request.merchantPassword,
                        request.amount, request.merchantOrderId, request.merchantTimestamp,
                        request.successUrl, request.failedUrl, request.errorUrl
                )*/, PaymentResponse.class);
        return  response;
    }
}
