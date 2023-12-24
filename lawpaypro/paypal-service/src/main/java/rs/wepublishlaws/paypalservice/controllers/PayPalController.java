package rs.wepublishlaws.paypalservice.controllers;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import rs.wepublishlaws.paypalservice.models.Subscription;
import rs.wepublishlaws.paypalservice.services.PaypalService;
import rs.wepublishlaws.paypalservice.services.subscriptions.SubscriptionService;
import rs.wepublishlaws.shared.GetPayPalSubRequest;
import rs.wepublishlaws.shared.GetPayPalSubResponse;
import rs.wepublishlaws.shared.PaymentStatus;
import rs.wepublishlaws.shared.UpdatePayPalSubRequest;
import rs.wepublishlaws.shared.messages.requests.PaymentStatusNotify;

@RestController
@RequestMapping("/api/paypal")
@RequiredArgsConstructor
public class PayPalController {
    private final SubscriptionService subscriptionService;
    private final PaypalService paypalService;
    private final RestTemplate restTemplate;
    @Value("${psp.notify.url}")
    private String notifyUrl;

    @GetMapping("/cancel")
    public RedirectView cancelPay() {
        return new RedirectView("http://localhost:4200/payment-cancelled");
        /*PaymentStatusNotify paymentStatusNotify = new PaymentStatusNotify(null, PaymentStatus.CANCEL);
        HttpEntity<PaymentStatusNotify> request = new HttpEntity<>(paymentStatusNotify);
        restTemplate.postForEntity(notifyUrl, request, RedirectView.class);*/
    }

    @GetMapping("/success")
    public RedirectView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")) {
                return new RedirectView("http://localhost:4200/payment-success/" + paymentId);
                /*PaymentStatusNotify paymentStatusNotify = new PaymentStatusNotify(payment.getId(), PaymentStatus.SUCCESS);
                HttpEntity<PaymentStatusNotify> request = new HttpEntity<>(paymentStatusNotify);
                restTemplate.postForObject(notifyUrl, request, Void.class);*/
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/subs")
    public ResponseEntity<GetPayPalSubResponse> getSubByUserAndOffer(@RequestBody GetPayPalSubRequest request) {
        Subscription sub = subscriptionService.getByUserAndOffer(request);
        GetPayPalSubResponse response = new GetPayPalSubResponse();
        response.setId(sub.getId());
        response.setPlanId(sub.getPlanId());
        if (sub.getSubscriptionId() != null) {
            response.setSubscriptionId(sub.getSubscriptionId());
            response.setSubscriptionStatus(sub.getSubscriptionStatus());
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/subs")
    public ResponseEntity<Boolean> updateSub(@RequestBody UpdatePayPalSubRequest request) {
        Subscription sub = subscriptionService.findOne(request.getId());
        subscriptionService.updateSub(sub, request.getSubscriptionId());
        return ResponseEntity.ok(true);
    }

    @PutMapping("/subs/cancel")
    public ResponseEntity<Boolean> cancel(@RequestBody UpdatePayPalSubRequest request) {
        Subscription sub = subscriptionService.findOne(request.getId());
        subscriptionService.cancelSub(sub, request.getSubscriptionId());
        return ResponseEntity.ok(true);
    }
}
