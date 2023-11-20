package rs.wepublishlaws.cryptoservice.consumers;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.messages.PaymentResponse;
import rs.wepublishlaws.shared.queues.QueueConstants;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CryptoConsumer {
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = QueueConstants.CRYPTO_SERVICE_QUEUE)
    public void receiveMessage(final Message message) throws JMSException {
        MessageConverter converter = jmsTemplate.getMessageConverter();
        PaymentMessage paymentMessage = (PaymentMessage) Objects.requireNonNull(converter).fromMessage(message);

        PaymentResponse response = new PaymentResponse();
        response.setPaymentUrl("Crypto service says hello.");
        response.setPaymentId(UUID.randomUUID().toString());

        jmsTemplate.send(message.getJMSReplyTo(), session -> Objects.requireNonNull(jmsTemplate.getMessageConverter()).toMessage(response, session));
    }
}
