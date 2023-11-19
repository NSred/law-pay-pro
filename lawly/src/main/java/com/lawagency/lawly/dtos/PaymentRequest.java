package com.lawagency.lawly.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaymentRequest {
    private Long offerId;
    private String paymentType;
    private String userId;
}
