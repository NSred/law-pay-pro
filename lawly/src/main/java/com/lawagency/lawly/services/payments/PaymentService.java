package com.lawagency.lawly.services.payments;

import com.lawagency.lawly.dtos.PaymentRequest;
import com.lawagency.lawly.dtos.responses.GetPayPalSubRequest;
import com.lawagency.lawly.dtos.responses.GetPayPalSubResponse;
import com.lawagency.lawly.dtos.responses.PaymentResponse;
import com.lawagency.lawly.dtos.responses.UpdatePayPalSubRequest;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
    GetPayPalSubResponse getPayPalSub(GetPayPalSubRequest request);
    boolean updateSub(UpdatePayPalSubRequest request);
    boolean cancelSub(UpdatePayPalSubRequest request);
}
