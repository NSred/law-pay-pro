package com.lawagency.lawly.controllers;

import com.lawagency.lawly.dtos.PaymentRequest;
import com.lawagency.lawly.services.payments.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest request) {
        String url = paymentService.processPayment(request);
        return ResponseEntity.ok(url);
    }
}
