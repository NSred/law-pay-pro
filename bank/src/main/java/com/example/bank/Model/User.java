package com.example.bank.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String address;
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    // Constructors, getters, setters...
}
