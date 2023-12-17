package rs.wepublishlaws.paymentserviceprovider.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.wepublishlaws.paymentserviceprovider.dto.PaymentMethodServiceDto;
import rs.wepublishlaws.paymentserviceprovider.service.PaymentMethodServiceService;

import java.util.List;

@RestController
@RequestMapping("api/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodServiceController {
    private final PaymentMethodServiceService service;

    @GetMapping
    public ResponseEntity<List<PaymentMethodServiceDto>> getAllByMerchant(@RequestParam("merchantId") String merchantId) {
        List<PaymentMethodServiceDto> paymentMethods = service.getAllByMerchant(merchantId);
        return ResponseEntity.ok(paymentMethods);
    }
}
