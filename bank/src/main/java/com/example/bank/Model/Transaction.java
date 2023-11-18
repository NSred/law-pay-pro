package com.example.bank.Model;

import jakarta.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column
    private String amount;

    @Column
    private String merchantOrderId;
    @Column
    private String merchantTimestamp;
    @Column
    private String acquirerOrderId;
    @Column
    private String acquirerTimestamp;
    @Column
    private String issuerOrderId;
    @Column
    private String issuerTimestamp;
    @Column
    private String status;
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

    // Constructors, getters, setters...
}
