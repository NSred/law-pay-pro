package com.example.bank.Controller;

import com.example.bank.DTO.PaymentDTO;
import com.example.bank.DTO.responses.PaymentResponse;
import com.example.bank.Service.PspService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/psp")
public class PspController {
    private final PspService pspService;

    @PostMapping("/process-payment")
    public ResponseEntity<?> processPayment(@RequestBody PaymentDTO paymentDTO){
        PaymentResponse response = pspService.processPayment(paymentDTO);
        return ResponseEntity.ok(response);
    }
}
