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
    public boolean transferMoney(Long accountIssuerId, Long MerchantId, Double amount) {
        Account accountIssuer = accountRepository.findById(accountIssuerId).orElse(null);
        Account accountMerchant = accountRepository.findByMerchantMerchantId(MerchantId).orElse(null);
        if (accountIssuer != null) {
            Double currentBalance = accountIssuer.getBalance();
            if (currentBalance.compareTo(amount) >= 0) {
                Double newBalance = currentBalance - amount;
                accountIssuer.setBalance(newBalance);
                accountRepository.save(accountIssuer);
            }
        }
        if (accountMerchant != null) {
            Double currentBalance = accountMerchant.getBalance();
            Double newBalance = currentBalance + amount;
            System.out.println(newBalance);
            accountMerchant.setBalance(newBalance);
            accountRepository.save(accountMerchant);
            return true;
        }


        return false;
    }
}
