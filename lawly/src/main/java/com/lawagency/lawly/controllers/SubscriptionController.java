package com.lawagency.lawly.controllers;

import com.lawagency.lawly.dtos.responses.GetPaymentMethodsResponse;
import com.lawagency.lawly.services.subscriptions.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/payment-methods")
    //@PreAuthorize("hasRole('USER')")
    //handluj bolje vracanje errora
    public ResponseEntity<Collection<String>> getPaymentMethodsByMerchant() {
        GetPaymentMethodsResponse response = subscriptionService.getPaymentMethodsByMerchant();
        if (response.isError())
            return ResponseEntity.badRequest().body(new ArrayList<>());
        return ResponseEntity.ok(response.getPaymentMethods());
    }
}
