package rs.wepublishlaws.cardpaymentservice;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @JmsListener(destination = "TESTIRANJE")
    public void receiveMessage(String message) {
        System.out.println(message);
    }
}
