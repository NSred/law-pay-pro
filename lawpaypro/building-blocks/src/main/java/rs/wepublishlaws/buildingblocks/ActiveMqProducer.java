package rs.wepublishlaws.buildingblocks;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActiveMqProducer implements IActiveMqProducer{

    private final JmsTemplate jmsTemplate;

    public <T> void sendMessage(String destination, T message) {
        jmsTemplate.convertAndSend(destination, message);
    }
}
