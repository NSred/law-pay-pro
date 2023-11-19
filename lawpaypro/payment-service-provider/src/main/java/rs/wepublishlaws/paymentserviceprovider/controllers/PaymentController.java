package rs.wepublishlaws.paymentserviceprovider.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.wepublishlaws.paymentserviceprovider.dto.PspPaymentRequest;
import rs.wepublishlaws.paymentserviceprovider.service.PaymentService;
import rs.wepublishlaws.shared.messages.PaymentResponse;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(value = "/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestHeader("API-Key") String apiKey, @RequestBody PspPaymentRequest request){
        PaymentResponse response = paymentService.processPayment(request, apiKey);
        return ResponseEntity.ok(response);
    }
}
