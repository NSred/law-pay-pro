package com.lawagency.lawly.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeRequestDto {
    private String qrCode;
    private String paymentId;
    private String accountNumber;
}
