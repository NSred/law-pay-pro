package com.example.bank.Model;

import com.example.bank.Model.Enum.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column
    private Double amount;

    @Column
    private String merchantOrderId;
    @Column
    private LocalDateTime merchantTimestamp;
    @Column
    private Long acquirerOrderId;
    @Column
    private LocalDateTime acquirerTimestamp;
    @Column
    private Long issuerOrderId;
    @Column
    private LocalDateTime issuerTimestamp;
    @Column
    private Status status;
    @Column
    private String paymentId;
    @Column
    private String acquirerAccountNumber;
    @Column
    private String issuerAccountNumber;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getIssuerAccountNumber() {
        return issuerAccountNumber;
    }

    public void setIssuerAccountNumber(String issuerAccountNumber) {
        this.issuerAccountNumber = issuerAccountNumber;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // Constructors, getters, setters...
}
