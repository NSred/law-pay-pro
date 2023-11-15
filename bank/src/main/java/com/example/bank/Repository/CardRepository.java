package com.example.bank.Repository;

import com.example.bank.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    // Additional query methods if needed
}
