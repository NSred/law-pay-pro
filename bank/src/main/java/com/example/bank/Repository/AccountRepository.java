package com.example.bank.Repository;

import com.example.bank.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // Additional query methods if needed
}
