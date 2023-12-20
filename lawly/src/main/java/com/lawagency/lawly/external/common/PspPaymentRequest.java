package com.lawagency.lawly.external.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PspPaymentRequest {
    private double amount;
    private String paymentType;
}
