package rs.wepublishlaws.paymentserviceprovider.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import rs.wepublishlaws.paymentserviceprovider.model.enums.PaymentType;

@Data
@RequiredArgsConstructor
public class PspPaymentRequest {
    private double amount;
    private String paymentType;
}
