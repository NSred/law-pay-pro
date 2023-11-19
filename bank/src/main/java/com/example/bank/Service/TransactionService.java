package com.example.bank.Service;

import com.example.bank.DTO.PCCRequestDTO;
import com.example.bank.DTO.PCCResponseDTO;
import com.example.bank.Model.Account;
import com.example.bank.Model.Enum.Status;
import com.example.bank.Model.Merchant;
import com.example.bank.Model.Transaction;
import com.example.bank.Repository.CardRepository;
import com.example.bank.Repository.MerchantRepository;
import com.example.bank.Repository.TransactionRepository;
import com.example.bank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final MerchantRepository merchantRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, MerchantRepository merchantRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.merchantRepository = merchantRepository;
        this.userRepository = userRepository;
    }

    public void insertTransactionAcquirer(PCCRequestDTO pccRequestDTO, Long merchantId){
        Transaction transaction = new Transaction();

        transaction.setAmount(pccRequestDTO.getAmount());
        transaction.setMerchantOrderId(pccRequestDTO.getMerchantOrderId());
        transaction.setMerchantTimestamp(pccRequestDTO.getMerchantTimestamp());
        transaction.setAcquirerOrderId(pccRequestDTO.getAcquirerOrderId());
        transaction.setAcquirerTimestamp(pccRequestDTO.getAcquirerTimestamp());
        transaction.setStatus(Status.PENDING);
        transaction.setPaymentId(pccRequestDTO.getPaymentId());
        transaction.setAcquirerAccountNumber(pccRequestDTO.getAcquirerAccountNumber());
        transaction.setMerchant(merchantRepository.getById(merchantId));

        transactionRepository.save(transaction);
    }
    public void insertTransactionIssuer(PCCResponseDTO pccResponseDTO, Long userId){
        Transaction transaction = new Transaction();

        transaction.setAmount(pccResponseDTO.getAmount());
        transaction.setMerchantOrderId(pccResponseDTO.getMerchantOrderId());
        transaction.setMerchantTimestamp(pccResponseDTO.getMerchantTimestamp());
        transaction.setAcquirerOrderId(pccResponseDTO.getAcquirerOrderId());
        transaction.setAcquirerTimestamp(pccResponseDTO.getAcquirerTimestamp());
        transaction.setStatus(Status.PENDING);
        transaction.setPaymentId(pccResponseDTO.getPaymentId());
        transaction.setAcquirerAccountNumber(pccResponseDTO.getAcquirerAccountNumber());
        transaction.setUser(userRepository.getById(userId));
        transaction.setIssuerOrderId(pccResponseDTO.getIssuerOrderId());
        transaction.setIssuerTimestamp(pccResponseDTO.getIssuerTimestamp());
        transaction.setIssuerAccountNumber(pccResponseDTO.getIssuerAccountNumber());

        transactionRepository.save(transaction);
    }


    public void updateTransaction(PCCResponseDTO pccResponseDTO) {
        Transaction transaction = transactionRepository.findByPaymentId(pccResponseDTO.getPaymentId()).orElse(null);
        transaction.setStatus(Status.SUCCESSFUL);
        transaction.setIssuerOrderId(pccResponseDTO.getIssuerOrderId());
        transaction.setIssuerTimestamp(pccResponseDTO.getIssuerTimestamp());
        transaction.setIssuerAccountNumber(pccResponseDTO.getIssuerAccountNumber());

        transactionRepository.save(transaction);
    }

    public void insertTransaction(PCCRequestDTO pccRequestDTO, long merchantId, String accNumberIss, long userId) {
        Transaction transaction = new Transaction();

        transaction.setAmount(pccRequestDTO.getAmount());
        transaction.setMerchantOrderId(pccRequestDTO.getMerchantOrderId());
        transaction.setMerchantTimestamp(pccRequestDTO.getMerchantTimestamp());
        transaction.setAcquirerOrderId(pccRequestDTO.getAcquirerOrderId());
        transaction.setAcquirerTimestamp(pccRequestDTO.getAcquirerTimestamp());
        transaction.setStatus(Status.SUCCESSFUL);
        transaction.setPaymentId(pccRequestDTO.getPaymentId());
        transaction.setAcquirerAccountNumber(pccRequestDTO.getAcquirerAccountNumber());
        transaction.setMerchant(merchantRepository.getById(merchantId));
        transaction.setIssuerAccountNumber(accNumberIss);
        transaction.setUser(userRepository.getById(userId));

        transactionRepository.save(transaction);
    }
}

