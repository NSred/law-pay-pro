package rs.wepublishlaws.paypalservice.consumers;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import rs.wepublishlaws.buildingblocks.handler.exceptions.HttpResponseException;
import rs.wepublishlaws.paypalservice.services.PaypalService;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.messages.PaymentResponse;
import rs.wepublishlaws.shared.queues.QueueConstants;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PayPalConsumer {
    private final JmsTemplate jmsTemplate;
    private final PaypalService paypalService;
    private static final String CURRENCY = "USD";
    private static final String METHOD = "paypal";
    private static final String INTENT = "sale";

    @JmsListener(destination = QueueConstants.PAYPAL_SERVICE_QUEUE)
    public void receiveMessage(final Message message) throws JMSException {
        MessageConverter converter = jmsTemplate.getMessageConverter();
        PaymentMessage paymentMessage = (PaymentMessage) Objects.requireNonNull(converter).fromMessage(message);

        PaymentResponse response = new PaymentResponse();
        try {
            Payment payment = paypalService.createPayment(paymentMessage.getAmount(), CURRENCY, METHOD, INTENT,
                    "", "http://localhost:8084/api/paypal/cancel", "http://localhost:8084/api/paypal/success");
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    response.setPaymentUrl(link.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            throw new HttpResponseException(e.getMessage());
        }

        jmsTemplate.send(message.getJMSReplyTo(), session -> Objects.requireNonNull(jmsTemplate.getMessageConverter()).toMessage(response, session));
    }
}
