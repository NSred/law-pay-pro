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

    public String getAccountNumberMerchant(Long merchantId){
        Account account = accountRepository.findByMerchantMerchantId(merchantId).orElse(null);
        return account.getAccountNumber();

    }
    public String getAccountNumberIssuer(Long issuerId){
        Account account = accountRepository.findByUserUserId(issuerId).orElse(null);
        return account.getAccountNumber();

    }

    @Transactional
    public boolean withdrawMoney(Long accountIssuerId, Double amount) {
        Account accountIssuer = accountRepository.findByUserUserId(accountIssuerId).orElse(null);
        if (accountIssuer != null) {
            Double currentBalance = accountIssuer.getBalance();
            if (currentBalance.compareTo(amount) >= 0) {
                Double newBalance = currentBalance - amount;
                System.out.println(newBalance);
                accountIssuer.setBalance(newBalance);
                accountRepository.save(accountIssuer);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean depositMoney(Long accountReceiverId, Double amount) {
        Account accountReceiver = accountRepository.findByMerchantMerchantId(accountReceiverId).orElse(null);
        if (accountReceiver != null) {
            Double currentBalance = accountReceiver.getBalance();
            Double newBalance = currentBalance + amount;
            System.out.println(newBalance);
            accountReceiver.setBalance(newBalance);
            accountRepository.save(accountReceiver);
            return true;
        }
        return false;
    }

    public Long getAccountId(String acquirerAccountNumber) {
        Account account = accountRepository.findByAccountNumber(acquirerAccountNumber).orElse(null);
        return account.getAccountId();
    }
}
