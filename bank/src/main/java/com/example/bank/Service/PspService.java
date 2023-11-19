package com.example.bank.Service;

import com.example.bank.DTO.PaymentDTO;
import com.example.bank.DTO.responses.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PspService {
    private final MerchantService merchantService;
    private final TransactionService transactionService;
    @Value("${bank.payment-url}")
    private String paymentUrl;
    @Value("${bank.fail-url}")
    private String failUrl;

    public PaymentResponse processPayment(PaymentDTO paymentDTO) {
        boolean isValidMerchant  = merchantService.existsByMerchantId(paymentDTO.getMerchantId());
        if (isValidMerchant){
            String paymentId = UUID.randomUUID().toString();
            transactionService.createBaseTransaction(paymentDTO, paymentId);
            return new PaymentResponse(paymentUrl, paymentId);
        }
        return new PaymentResponse(failUrl, null);
    }
}
