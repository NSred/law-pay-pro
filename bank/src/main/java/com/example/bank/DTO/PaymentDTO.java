package com.example.bank.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class PaymentDTO {
    public String merchantId;
    public String merchantPassword;
    public Double amount;
    public String merchantOrderId;
    public LocalDateTime merchantTimestamp;
    public String successUrl;
    public String failedUrl;
    public String errorUrl;
}
