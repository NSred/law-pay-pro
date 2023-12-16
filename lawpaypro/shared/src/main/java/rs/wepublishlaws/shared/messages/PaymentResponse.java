package rs.wepublishlaws.shared.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import rs.wepublishlaws.shared.PaymentStatus;
import rs.wepublishlaws.shared.SdkParamsDto;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String paymentUrl;
    private String paymentId;
    private PaymentStatus status;
    private SdkParamsDto sdkParams;
    private String errorMessage;
    private String errorCode;
}
