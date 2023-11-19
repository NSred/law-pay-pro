package rs.wepublishlaws.paymentserviceprovider.service;

import rs.wepublishlaws.paymentserviceprovider.dto.PspPaymentRequest;
import rs.wepublishlaws.shared.messages.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PspPaymentRequest request, String apiKey);
}
