package com.example.bank.DTO;

import java.time.LocalDateTime;

public class PSPResponseDTO {
    private String url;
    private Long acquirerOrderId;
    private LocalDateTime acquirerTimestamp;
    private String merchantOrderId;
    private String paymentId;

    public PSPResponseDTO(String url) {
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

    public PSPResponseDTO(String url, Long acquirerOrderId, LocalDateTime acquirerTimestamp, String merchantOrderId, String paymentId) {
        this.url = url;
        this.acquirerOrderId = acquirerOrderId;
        this.acquirerTimestamp = acquirerTimestamp;
        this.merchantOrderId = merchantOrderId;
        this.paymentId = paymentId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getAcquirerOrderId() {
        return acquirerOrderId;
    }

    public void setAcquirerOrderId(Long acquirerOrderId) {
        this.acquirerOrderId = acquirerOrderId;
    }

    public LocalDateTime getAcquirerTimestamp() {
        return acquirerTimestamp;
    }

    public void setAcquirerTimestamp(LocalDateTime acquirerTimestamp) {
        this.acquirerTimestamp = acquirerTimestamp;
    }

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
