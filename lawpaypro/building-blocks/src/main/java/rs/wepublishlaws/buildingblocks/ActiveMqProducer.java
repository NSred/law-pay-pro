package rs.wepublishlaws.buildingblocks;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.buildingblocks.handler.exceptions.MessageNullException;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActiveMqProducer implements IProducer {

    private final JmsTemplate jmsTemplate;

    public <T> void sendMessage(String destination, T message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    public <T, R> R sendAndReceive(String destination, T request, Class<R> responseType) {
        Message responseMessage = jmsTemplate.sendAndReceive(destination, session -> {
            try {
                Message message = jmsTemplate.getMessageConverter().toMessage(request, session);
                message.setJMSCorrelationID(UUID.randomUUID().toString());
                return message;
            } catch (NullPointerException e){
                throw new MessageNullException("Poruka ne sme biti null");
            }
//            Message message = Objects.requireNonNull(jmsTemplate.getMessageConverter()).toMessage(request, session);
//            message.setJMSCorrelationID(UUID.randomUUID().toString());
//            return message;
        });

        if (responseMessage != null) {
            try {
                Object response = Objects.requireNonNull(jmsTemplate.getMessageConverter()).fromMessage(responseMessage);
                if (responseType.isInstance(response)) {
                    return responseType.cast(response);
                } else {
                    throw new IllegalArgumentException("Response is not of the expected type: " + responseType.getName());
                }
            } catch (JMSException e) {
                // handle exception, possibly rethrow as a runtime exception or custom exception
            }
        }
        return null;
    }
}
