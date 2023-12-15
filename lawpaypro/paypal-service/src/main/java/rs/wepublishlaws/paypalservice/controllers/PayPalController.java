package rs.wepublishlaws.paypalservice.controllers;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import rs.wepublishlaws.paypalservice.services.PaypalService;
import rs.wepublishlaws.shared.PaymentStatus;
import rs.wepublishlaws.shared.messages.requests.PaymentStatusNotify;

@RestController
@RequestMapping("/api/paypal")
@RequiredArgsConstructor
public class PayPalController {
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
}
