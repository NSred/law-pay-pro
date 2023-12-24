package com.lawagency.lawly.controllers;

import com.lawagency.lawly.dtos.PaymentRequest;
import com.lawagency.lawly.dtos.responses.GetPayPalSubRequest;
import com.lawagency.lawly.dtos.responses.GetPayPalSubResponse;
import com.lawagency.lawly.dtos.responses.PaymentResponse;
import com.lawagency.lawly.dtos.responses.UpdatePayPalSubRequest;
import com.lawagency.lawly.services.payments.PaymentService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/payPalSubs")
    public ResponseEntity<GetPayPalSubResponse> getPayPalSub(@RequestBody GetPayPalSubRequest request) {
        GetPayPalSubResponse response = paymentService.getPayPalSub(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/payPalSubs")
    public ResponseEntity<Boolean> update(@RequestBody UpdatePayPalSubRequest request) {
        boolean response = paymentService.updateSub(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/payPalSubs/cancel")
    public ResponseEntity<Boolean> cancel(@RequestBody UpdatePayPalSubRequest request) {
        boolean response = paymentService.cancelSub(request);
        return ResponseEntity.ok(response);
    }
}
