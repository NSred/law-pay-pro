package com.lawagency.lawly.external.common;

import com.lawagency.lawly.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PspPaymentRequest {
    private double amount;
    private PaymentType paymentType;
}
