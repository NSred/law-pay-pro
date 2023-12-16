package rs.wepublishlaws.paymentserviceprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.buildingblocks.IProducer;
import rs.wepublishlaws.paymentserviceprovider.dto.PspPaymentRequest;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.messages.PaymentResponse;
import rs.wepublishlaws.shared.queues.QueueConstants;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final IProducer producer;
    private final MerchantService merchantService;
    @Value("${url-configuration.error_url}")
    private String errorUrl;
    @Value("${url-configuration.failed_url}")
    private String failedUrl;
    @Value("${url-configuration.success_url}")
    private String successUrl;

    public PaymentResponse processPayment(PspPaymentRequest request, String apiKey) {

        Merchant merchant = merchantService.findMerchantByApiKey(apiKey);
        if (merchant == null)
//            return new PaymentResponse(errorUrl, null);
            throw new RuntimeException();
        String destination = switch (request.getPaymentType()) {
            case QR_CODE -> QueueConstants.QRCODE_SERVICE_QUEUE;
            case PAY_PAL -> QueueConstants.PAYPAL_SERVICE_QUEUE;
            case CRYPTO_CURRENCY -> QueueConstants.CRYPTO_SERVICE_QUEUE;
            default -> QueueConstants.CARD_SERVICE_QUEUE;
        };
        return producer.sendAndReceive(
                destination,
                new PaymentMessage(merchant.getMerchantId(), merchant.getMerchantPassword(),
                        request.getAmount(), UUID.randomUUID(), LocalDateTime.now(),
                        successUrl, failedUrl, errorUrl),
                PaymentResponse.class);
    }
}
