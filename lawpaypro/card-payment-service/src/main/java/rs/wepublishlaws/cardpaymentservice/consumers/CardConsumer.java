package rs.wepublishlaws.cardpaymentservice.consumers;

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
import rs.wepublishlaws.shared.queues.QueueConstants;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CardConsumer {
    private final JmsTemplate jmsTemplate;
    private final RestTemplate restTemplate;
    @Value("${bank.url}")
    private String bankUrl;
    @JmsListener(destination = QueueConstants.CARD_SERVICE_QUEUE)
    public void receiveMessage(final Message message) throws JMSException {
        MessageConverter converter = jmsTemplate.getMessageConverter();
        PaymentMessage paymentMessage = (PaymentMessage) Objects.requireNonNull(converter).fromMessage(message);
        System.out.println(paymentMessage);

        try{

            //HTTP poziv Bank aplikacije koja vraca Url string
            HttpEntity<PaymentMessage> bankRequest = new HttpEntity<>(paymentMessage);
            String responseUrl = restTemplate.postForObject(bankUrl, bankRequest, String.class);

            /*PaymentResponse response = new PaymentResponse();
            response.setMessage("Card consumer received a message.");
            response.setDateTime(LocalDateTime.now());*/

            jmsTemplate.send(message.getJMSReplyTo(), session ->
                    Objects.requireNonNull(jmsTemplate.getMessageConverter())
                            .toMessage("Card service pogodjen", session)
            );
        } catch (NullPointerException e){
            throw new HttpResponseException(e.getMessage());
        }
    }
}
