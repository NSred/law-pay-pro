package com.example.bank.DTO.responses;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentResponseQR {
    private String paymentUrl;
    @Nullable
    private String paymentId;
    @Nullable
    private String qrCode;
}