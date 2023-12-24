package com.lawagency.lawly.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class PaymentRequest {
    private UUID offerId;
    private String paymentType;
    private Long userId;
}
