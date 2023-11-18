package com.example.bank.Service;

import com.example.bank.Model.Account;
import com.example.bank.Repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public boolean withdrawMoney(Long accountIssuerId, Double amount) {
        Account accountIssuer = accountRepository.findById(accountIssuerId).orElse(null);
        if (accountIssuer != null) {
            Double currentBalance = accountIssuer.getBalance();
            if (currentBalance.compareTo(amount) >= 0) {
                Double newBalance = currentBalance - amount;
                accountIssuer.setBalance(newBalance);
                accountRepository.save(accountIssuer);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean depositMoney(Long accountReceiverId, Double amount) {
        Account accountReceiver = accountRepository.findById(accountReceiverId).orElse(null);
        if (accountReceiver != null) {
            Double currentBalance = accountReceiver.getBalance();
            Double newBalance = currentBalance + amount;
            accountReceiver.setBalance(newBalance);
            accountRepository.save(accountReceiver);
            return true;
        }
        return false;
    }
}
