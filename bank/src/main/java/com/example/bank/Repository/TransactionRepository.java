package com.example.bank.Repository;

import com.example.bank.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByPaymentId(String paymentId);
    // Additional query methods if needed
}

