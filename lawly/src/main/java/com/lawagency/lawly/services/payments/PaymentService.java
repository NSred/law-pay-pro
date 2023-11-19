package com.lawagency.lawly.services.payments;

import com.lawagency.lawly.dtos.PaymentRequest;

public interface PaymentService {
    String processPayment(PaymentRequest request);
}
