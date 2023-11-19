package com.lawagency.lawly.services.payments;

import com.lawagency.lawly.dtos.PaymentRequest;
import com.lawagency.lawly.dtos.responses.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
}
