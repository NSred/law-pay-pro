package com.example.bank.Service;

import com.example.bank.DTO.*;
import com.example.bank.Model.Enum.Status;
import com.example.bank.Model.Transaction;
import com.example.bank.Repository.MerchantRepository;
import com.example.bank.Repository.TransactionRepository;
import com.example.bank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public void createBaseTransaction(PaymentDTO request, String paymentId){
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setMerchantOrderId(request.getMerchantOrderId());
        transaction.setMerchantTimestamp(request.getMerchantTimestamp());
        transaction.setStatus(Status.PENDING);
        transaction.setPaymentId(paymentId);
        transaction.setMerchant(merchantRepository.findMerchantByMerchantId(request.merchantId));
        transactionRepository.save(transaction);
    }

    public void insertTransactionAcquirer(PCCRequestDTO pccRequestDTO){
        Transaction transaction = transactionRepository.findByPaymentId(pccRequestDTO.getPaymentId()).orElse(null);

        transaction.setAmount(pccRequestDTO.getAmount());
        transaction.setMerchantOrderId(pccRequestDTO.getMerchantOrderId());
        transaction.setMerchantTimestamp(pccRequestDTO.getMerchantTimestamp());
        transaction.setAcquirerOrderId(pccRequestDTO.getAcquirerOrderId());
        transaction.setAcquirerTimestamp(pccRequestDTO.getAcquirerTimestamp());
        transaction.setStatus(Status.PENDING);
        transaction.setPaymentId(pccRequestDTO.getPaymentId());
        transaction.setAcquirerAccountNumber(pccRequestDTO.getAcquirerAccountNumber());

        transactionRepository.save(transaction);
    }
    public void insertTransactionAcquirerQR(PCCRequestQRDTO pccRequestQRDTO){
        Transaction transaction = transactionRepository.findByPaymentId(pccRequestQRDTO.getPaymentId()).orElse(null);

        transaction.setAmount(pccRequestQRDTO.getAmount());
        transaction.setMerchantOrderId(pccRequestQRDTO.getMerchantOrderId());
        transaction.setMerchantTimestamp(pccRequestQRDTO.getMerchantTimestamp());
        transaction.setAcquirerOrderId(pccRequestQRDTO.getAcquirerOrderId());
        transaction.setAcquirerTimestamp(pccRequestQRDTO.getAcquirerTimestamp());
        transaction.setStatus(Status.PENDING);
        transaction.setPaymentId(pccRequestQRDTO.getPaymentId());
        transaction.setAcquirerAccountNumber(pccRequestQRDTO.getAcquirerAccountNumber());

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
    public void insertTransactionIssuerQR(PCCResponseQRDTO pccResponseQRDTO, Long userId){
        Transaction transaction = new Transaction();

        transaction.setAmount(pccResponseQRDTO.getAmount());
        transaction.setMerchantOrderId(pccResponseQRDTO.getMerchantOrderId());
        transaction.setMerchantTimestamp(pccResponseQRDTO.getMerchantTimestamp());
        transaction.setAcquirerOrderId(pccResponseQRDTO.getAcquirerOrderId());
        transaction.setAcquirerTimestamp(pccResponseQRDTO.getAcquirerTimestamp());
        transaction.setStatus(Status.PENDING);
        transaction.setPaymentId(pccResponseQRDTO.getPaymentId());
        transaction.setAcquirerAccountNumber(pccResponseQRDTO.getAcquirerAccountNumber());
        transaction.setUser(userRepository.getById(userId));
        transaction.setIssuerOrderId(pccResponseQRDTO.getIssuerOrderId());
        transaction.setIssuerTimestamp(pccResponseQRDTO.getIssuerTimestamp());
        transaction.setIssuerAccountNumber(pccResponseQRDTO.getIssuerAccountNumber());

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
    public void updateTransactionQR(PCCResponseQRDTO pccResponseQRDTO) {
        Transaction transaction = transactionRepository.findByPaymentId(pccResponseQRDTO.getPaymentId()).orElse(null);
        transaction.setStatus(Status.SUCCESSFUL);
        transaction.setIssuerOrderId(pccResponseQRDTO.getIssuerOrderId());
        transaction.setIssuerTimestamp(pccResponseQRDTO.getIssuerTimestamp());
        transaction.setIssuerAccountNumber(pccResponseQRDTO.getIssuerAccountNumber());

        transactionRepository.save(transaction);
    }

    public void insertTransaction(PCCRequestDTO pccRequestDTO, String accNumberIss, long userId) {
        Transaction transaction = transactionRepository.findByPaymentId(pccRequestDTO.getPaymentId()).orElse(null);

        transaction.setAmount(pccRequestDTO.getAmount());
        transaction.setMerchantOrderId(pccRequestDTO.getMerchantOrderId());
        transaction.setMerchantTimestamp(pccRequestDTO.getMerchantTimestamp());
        transaction.setAcquirerOrderId(pccRequestDTO.getAcquirerOrderId());
        transaction.setAcquirerTimestamp(pccRequestDTO.getAcquirerTimestamp());
        transaction.setStatus(Status.SUCCESSFUL);
        transaction.setPaymentId(pccRequestDTO.getPaymentId());
        transaction.setAcquirerAccountNumber(pccRequestDTO.getAcquirerAccountNumber());
        transaction.setIssuerAccountNumber(accNumberIss);
        transaction.setUser(userRepository.getById(userId));

        transactionRepository.save(transaction);
    }

    public Transaction getTransactionbyPaymentId(String paymentId) {
        return transactionRepository.findByPaymentId(paymentId).orElse(null);
    }
}

