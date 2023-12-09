package rs.wepublishlaws.paypalservice.controllers;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import rs.wepublishlaws.paypalservice.services.PaypalService;

@RestController
@RequestMapping("/api/paypal")
@RequiredArgsConstructor
public class PayPalController {
    private final PaypalService paypalService;

    @GetMapping("/cancel")
    public RedirectView cancelPay() {
        return new RedirectView("http://localhost:4200/payment-cancelled");
    }

    @GetMapping("/success")
    public RedirectView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment);
            if(payment.getState().equals("approved")) {
                return new RedirectView("http://localhost:4200/payment-success/" + payment.getId());
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return null;
    }
}
