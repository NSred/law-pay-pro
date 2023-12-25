package com.example.bank.DTO;

import java.time.LocalDateTime;

public class PCCResponseQRDTO {

    private Double amount;
    private Long acquirerOrderId;
    private LocalDateTime acquirerTimestamp;
    private Long issuerOrderId;
    private LocalDateTime issuerTimestamp;
    private String bankId;
    private String merchantOrderId;
    private LocalDateTime merchantTimestamp;
    private String paymentId;
    private String acquirerAccountNumber;
    private String issuerAccountNumber;

    public PCCResponseQRDTO( Double amount, Long acquirerOrderId, LocalDateTime acquirerTimestamp, Long issuerOrderId, LocalDateTime issuerTimestamp, String bankId, String merchantOrderId, LocalDateTime merchantTimestamp, String paymentId, String acquirerAccountNumber, String issuerAccountNumber) {
        this.amount = amount;
        this.acquirerOrderId = acquirerOrderId;
        this.acquirerTimestamp = acquirerTimestamp;
        this.issuerOrderId = issuerOrderId;
        this.issuerTimestamp = issuerTimestamp;
        this.bankId = bankId;
        this.merchantOrderId = merchantOrderId;
        this.merchantTimestamp = merchantTimestamp;
        this.paymentId = paymentId;
        this.acquirerAccountNumber = acquirerAccountNumber;
        this.issuerAccountNumber = issuerAccountNumber;
    }

    public PCCResponseQRDTO() {

    }

    public Long getIssuerOrderId() {
        return issuerOrderId;
    }

    public void setIssuerOrderId(Long issuerOrderId) {
        this.issuerOrderId = issuerOrderId;
    }

    public LocalDateTime getIssuerTimestamp() {
        return issuerTimestamp;
    }

    public void setIssuerTimestamp(LocalDateTime issuerTimestamp) {
        this.issuerTimestamp = issuerTimestamp;
    }

    public String getIssuerAccountNumber() {
        return issuerAccountNumber;
    }

    public void setIssuerAccountNumber(String issuerAccountNumber) {
        this.issuerAccountNumber = issuerAccountNumber;
    }


        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
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

        public String getBankId() {
            return bankId;
        }

        public void setBankId(String bankId) {
            this.bankId = bankId;
        }

        public String getMerchantOrderId() {
            return merchantOrderId;
        }

        public void setMerchantOrderId(String merchantOrderId) {
            this.merchantOrderId = merchantOrderId;
        }

        public LocalDateTime getMerchantTimestamp() {
            return merchantTimestamp;
        }

        public void setMerchantTimestamp(LocalDateTime merchantTimestamp) {
            this.merchantTimestamp = merchantTimestamp;
        }

        public String getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getAcquirerAccountNumber() {
            return acquirerAccountNumber;
        }

        public void setAcquirerAccountNumber(String acquirerAccountNumber) {
            this.acquirerAccountNumber = acquirerAccountNumber;
        }
}
