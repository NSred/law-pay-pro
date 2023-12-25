package com.lawagency.lawly.services.payments;

import com.lawagency.lawly.dtos.PaymentRequest;
import com.lawagency.lawly.dtos.responses.*;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
    GetPayPalSubResponse getPayPalSub(GetPayPalSubRequest request);
    boolean updateSub(UpdatePayPalSubRequest request);
    boolean cancelSub(UpdatePayPalSubRequest request);
    QrCodeResponseDto payQr(QrCodeRequestDto request);
}
