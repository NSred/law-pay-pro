package com.lawagency.lawly.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Role role;
}