package com.example.bank.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long merchantId;

    @Column
    private String merchantPassword;

    @OneToMany(mappedBy = "merchant")
    private List<Account> accounts;
    @OneToMany(mappedBy = "merchant")
    private List<Transaction> transactions;
    // Constructors, getters, setters...
}

