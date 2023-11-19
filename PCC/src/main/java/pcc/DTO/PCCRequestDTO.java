package pcc.DTO;

import java.time.LocalDateTime;

public class PCCRequestDTO {

    private String pan;
    private String securityCode;
    private String cardHolderName;
    private String expirationDate;
    private Double amount;
    private Long acquirerOrderId;
    private LocalDateTime acquirerTimestamp;
    private String bankId;
    private String merchantOrderId;
    private LocalDateTime merchantTimestamp;
    private String paymentId;
    private String acquirerAccountNumber;

    public PCCRequestDTO(String pan, String securityCode, String cardHolderName, String expirationDate, Double amount, Long acquirerOrderId, LocalDateTime acquirerTimestamp, String bankId, String merchantOrderId, LocalDateTime merchantTimestamp, String paymentId, String acquirerAccountNumber) {
        this.pan = pan;
        this.securityCode = securityCode;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.acquirerOrderId = acquirerOrderId;
        this.acquirerTimestamp = acquirerTimestamp;
        this.bankId = bankId;
        this.merchantOrderId = merchantOrderId;
        this.merchantTimestamp = merchantTimestamp;
        this.paymentId = paymentId;
        this.acquirerAccountNumber = acquirerAccountNumber;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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
