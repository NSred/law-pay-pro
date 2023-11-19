package rs.wepublishlaws.shared.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String paymentUrl;
    private String paymentId;
}
