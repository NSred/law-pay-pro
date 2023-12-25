package com.example.bank.DTO.responses;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String paymentUrl;
    private String paymentId;
    private SdkParamsDto sdkParams;
    private String errorMessage;
    private String errorCode;
}
