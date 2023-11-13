package rs.wepublishlaws.cardpaymentservice.consumers;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import rs.wepublishlaws.shared.messages.PaymentResponse;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.queues.QueueConstants;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CardConsumer {
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = QueueConstants.CARD_SERVICE_QUEUE)
    public void receiveMessage(final Message message) throws JMSException {
        MessageConverter converter = jmsTemplate.getMessageConverter();
        PaymentMessage paymentMessage = (PaymentMessage) Objects.requireNonNull(converter).fromMessage(message);
        System.out.println(paymentMessage);

        PaymentResponse response = new PaymentResponse();
        response.setMessage("Card consumer received a message.");
        response.setDateTime(LocalDateTime.now());

        jmsTemplate.send(message.getJMSReplyTo(), session -> Objects.requireNonNull(jmsTemplate.getMessageConverter()).toMessage(response, session));
    }
}
