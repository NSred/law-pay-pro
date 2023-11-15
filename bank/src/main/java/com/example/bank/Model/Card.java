package com.example.bank.Model;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    @Column
    private String pan;
    @Column
    private String securityCode;
    @Column
    private String cardHolderName;
    @Column
    private String expirationDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Constructors, getters, setters...
}
