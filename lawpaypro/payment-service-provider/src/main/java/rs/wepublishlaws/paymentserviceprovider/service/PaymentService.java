package rs.wepublishlaws.paymentserviceprovider.service;

import rs.wepublishlaws.paymentserviceprovider.dto.PspPaymentRequest;

public interface PaymentService {
    String processPayment(PspPaymentRequest request, String apiKey);
}
