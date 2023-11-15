package com.example.bank.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column
    private String accountNumber;
    @Column
    private Double balance;
    @Column
    private Double reserved;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;


    // Constructors, getters, setters...
}
