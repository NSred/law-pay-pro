package rs.wepublishlaws.shared.messages.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import rs.wepublishlaws.shared.PaymentStatus;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentStatusNotify {
    private String paymentId;
    private PaymentStatus paymentStatus;
}
