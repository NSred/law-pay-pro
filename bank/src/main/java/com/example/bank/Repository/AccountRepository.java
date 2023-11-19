package com.example.bank.Repository;

import com.example.bank.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByMerchantMerchantId(Long merchantId);

    Optional<Account> findByUserUserId(Long userId);

    Optional<Account> findByAccountNumber(String accountNumber);
}
