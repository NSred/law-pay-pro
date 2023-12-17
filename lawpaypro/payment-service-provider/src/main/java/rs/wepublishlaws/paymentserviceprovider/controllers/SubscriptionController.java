package rs.wepublishlaws.paymentserviceprovider.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.wepublishlaws.paymentserviceprovider.dto.SubscriptionRequest;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;
import rs.wepublishlaws.paymentserviceprovider.service.MerchantService;
import rs.wepublishlaws.paymentserviceprovider.service.SubscriptionService;
import rs.wepublishlaws.shared.messages.GetPaymentMethodsMessage;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final MerchantService merchantService;

    @GetMapping
    public ResponseEntity<GetPaymentMethodsMessage> getSubscriptionsByMerchant(
            @RequestHeader("API-Key") String apiKey, @QueryParam("merchantId") String merchantId){
        //provera api key-a
        Merchant merchant = merchantService.findMerchantByApiKey(apiKey);
        if (merchant == null)
            return ResponseEntity.ok(new GetPaymentMethodsMessage(new ArrayList<>(), true, "Invalid Api key"));
        Collection<String> subscriptions = subscriptionService.getPaymentMethodsByMerchant(merchantId);
        return ResponseEntity.ok(new GetPaymentMethodsMessage(
                subscriptions, false, ""));
    }

    @PutMapping("/subscribe")
    public ResponseEntity<Void> subscribe(
            @RequestBody SubscriptionRequest subscriptionRequest
    ) throws JsonProcessingException {

       subscriptionService.makeSubscription(
               subscriptionRequest.getMerchantId(),
               subscriptionRequest.getPaymentMethod(),
               subscriptionRequest.getSubscriptionType()
       );
        return ResponseEntity.ok().build();
    }
}
