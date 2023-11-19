package com.lawagency.lawly.dtos.responses;

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
