package com.example.bank.Service;

import com.example.bank.DTO.PSPResponseDTO;
import com.example.bank.DTO.PaymentDTO;
import com.example.bank.DTO.responses.PaymentResponse;
import com.example.bank.Model.Enum.Url;
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
    @Value("${bank.success-url}")
    private String successUrl;
    @Value("${bank.error-url}")
    private String errorUrl;

    public PaymentResponse processPayment(PaymentDTO paymentDTO) {
        boolean isValidMerchant  = merchantService.existsByMerchantId(paymentDTO.getMerchantId());
        if (isValidMerchant){
            String paymentId = UUID.randomUUID().toString();
            transactionService.createBaseTransaction(paymentDTO, paymentId);
            return new PaymentResponse(paymentUrl, paymentId);
        }
        return new PaymentResponse(failUrl, null);
    }
    public PSPResponseDTO response(Url url){
        if(url==Url.SUCCESSFUL)
            return new PSPResponseDTO(successUrl);
        else if(url == Url.ERROR)
            return new PSPResponseDTO(errorUrl);
        else
            return new PSPResponseDTO(failUrl);

    }
}
