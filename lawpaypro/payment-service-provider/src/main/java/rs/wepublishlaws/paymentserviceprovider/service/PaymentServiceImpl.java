package rs.wepublishlaws.paymentserviceprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.buildingblocks.IProducer;
import rs.wepublishlaws.paymentserviceprovider.dto.PspPaymentRequest;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;
import rs.wepublishlaws.paymentserviceprovider.model.PaymentMethodService;
import rs.wepublishlaws.shared.QrCodeRequestDto;
import rs.wepublishlaws.shared.QrCodeResponseDto;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.messages.PaymentResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final IProducer producer;
    private final MerchantService merchantService;
    private final PaymentMethodServiceService paymentMethodService;
    @Value("${url-configuration.error_url}")
    private String errorUrl;
    @Value("${url-configuration.failed_url}")
    private String failedUrl;
    @Value("${url-configuration.success_url}")
    private String successUrl;

    public PaymentResponse processPayment(PspPaymentRequest request, String apiKey) {

        Merchant merchant = merchantService.findMerchantByApiKey(apiKey);
        if (merchant == null)
            throw new RuntimeException();

        List<PaymentMethodService> paymentMethods = paymentMethodService.getAll();
        PaymentMethodService selectedMethod = paymentMethods
                .stream()
                .filter(method -> method.getName().equals(request.getPaymentType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No matching payment method found."));

        String destination = selectedMethod.getQueueName();
        /*String destination = switch (request.getPaymentType()) {
            case "QR_CODE" -> QueueConstants.QRCODE_SERVICE_QUEUE;
            case "PAY_PAL" -> QueueConstants.PAYPAL_SERVICE_QUEUE;
            case "CRYPTO_CURRENCY" -> QueueConstants.CRYPTO_SERVICE_QUEUE;
            default -> QueueConstants.CARD_SERVICE_QUEUE;
        };*/
        return producer.sendAndReceive(
                destination,
                new PaymentMessage(merchant.getMerchantId(), merchant.getMerchantPassword(),
                        request.getAmount(), UUID.randomUUID(), LocalDateTime.now(),
                        successUrl, failedUrl, errorUrl),
                PaymentResponse.class);
    }
}
