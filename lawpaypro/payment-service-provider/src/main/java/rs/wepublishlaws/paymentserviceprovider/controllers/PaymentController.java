package rs.wepublishlaws.paymentserviceprovider.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import rs.wepublishlaws.paymentserviceprovider.dto.PspPaymentRequest;
import rs.wepublishlaws.paymentserviceprovider.service.PaymentService;
import rs.wepublishlaws.shared.*;
import rs.wepublishlaws.shared.messages.PaymentResponse;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final RestTemplate restTemplate;
    private final PaymentService paymentService;

    @PostMapping(value = "/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestHeader("API-Key") String apiKey, @RequestBody PspPaymentRequest request){
        PaymentResponse response = paymentService.processPayment(request, apiKey);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/pay-pal-subs")
    public ResponseEntity<GetPayPalSubResponse> getPayPalSub(@RequestBody GetPayPalSubRequest request) {
        GetPayPalSubResponse res;
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<GetPayPalSubRequest> message = new HttpEntity<>(request, headers);
            String url = "http://localhost:8084/api/paypal/subs";
            ResponseEntity<GetPayPalSubResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    message,
                    GetPayPalSubResponse.class);
            res = response.getBody();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(res);
    }

    @PutMapping("/pay-pal-subs")
    public ResponseEntity<Boolean> updateSub(@RequestBody UpdatePayPalSubRequest request) {
        boolean res;
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<UpdatePayPalSubRequest> message = new HttpEntity<>(request, headers);
            String url = "http://localhost:8084/api/paypal/subs";
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    message,
                    Boolean.class);
            res = Boolean.TRUE.equals(response.getBody());
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(res);
    }

    @PutMapping("/pay-pal-subs/cancel")
    public ResponseEntity<Boolean> cancel(@RequestBody UpdatePayPalSubRequest request) {
        boolean res;
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<UpdatePayPalSubRequest> message = new HttpEntity<>(request, headers);
            String url = "http://localhost:8084/api/paypal/subs/cancel";
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    message,
                    Boolean.class);
            res = Boolean.TRUE.equals(response.getBody());
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping("/pay-qr")
    public ResponseEntity<QrCodeResponseDto> payQr(@RequestBody QrCodeRequestDto request) {
        QrCodeResponseDto res;
        try {
            request.setAccountNumber("432423");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<QrCodeRequestDto> message = new HttpEntity<>(request, headers);
            String url = "http://localhost:8060/bank/payQR";
            ResponseEntity<QrCodeResponseDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    message,
                    QrCodeResponseDto.class);
            res = response.getBody();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(res);
    }
}
