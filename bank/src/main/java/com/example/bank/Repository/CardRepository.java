package com.example.bank.Repository;

import com.example.bank.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardHolderNameAndExpirationDate(
            String cardHolderName, String expirationDate);
}
