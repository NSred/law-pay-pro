package rs.wepublishlaws.qrcodeservice.consumers;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rs.wepublishlaws.buildingblocks.handler.exceptions.HttpResponseException;
import rs.wepublishlaws.shared.messages.PaymentMessage;
import rs.wepublishlaws.shared.messages.PaymentResponse;
import rs.wepublishlaws.shared.queues.QueueConstants;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QrCodeConsumer {
    private final JmsTemplate jmsTemplate;
    private final RestTemplate restTemplate;
    @Value("${bank.url}")
    private String bankUrl;

    @JmsListener(destination = QueueConstants.QRCODE_SERVICE_QUEUE)
    public void receiveMessage(final Message message) throws JMSException {
        MessageConverter converter = jmsTemplate.getMessageConverter();
        PaymentMessage paymentMessage = (PaymentMessage) Objects.requireNonNull(converter).fromMessage(message);

        try{
            //HTTP poziv Bank aplikacije koja vraca Url string
            HttpEntity<PaymentMessage> bankRequest = new HttpEntity<>(paymentMessage);
            PaymentResponse response = restTemplate.postForObject(bankUrl, bankRequest, PaymentResponse.class);

            jmsTemplate.send(message.getJMSReplyTo(), session ->
                    Objects.requireNonNull(jmsTemplate.getMessageConverter())
                            .toMessage(response, session)
            );
        } catch (NullPointerException e){
            throw new HttpResponseException(e.getMessage());
        }
    }
}
