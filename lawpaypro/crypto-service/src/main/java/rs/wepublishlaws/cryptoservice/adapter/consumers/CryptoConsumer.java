package rs.wepublishlaws.cryptoservice.adapter.consumers;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import rs.wepublishlaws.cryptoservice.adapter.mapper.CoinspaidMapper;
import rs.wepublishlaws.cryptoservice.domain.model.deposit.CoinspaidDepositResponseDto;
import rs.wepublishlaws.cryptoservice.domain.service.PaymentService;
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
    private final CoinspaidMapper coinspaidMapper;
    private final PaymentService paymentService;

    @JmsListener(destination = QueueConstants.CRYPTO_SERVICE_QUEUE)
    public void receiveMessage(final Message message) throws JMSException {
        MessageConverter converter = jmsTemplate.getMessageConverter();
        PaymentMessage paymentMessage = (PaymentMessage) Objects.requireNonNull(converter).fromMessage(message);

          PaymentResponse response = new PaymentResponse();
//        response.setPaymentUrl("Crypto service says hello.");
//        response.setPaymentId(UUID.randomUUID().toString());
        try{
            response = coinspaidMapper.mapFromTransactionResponse(
                    paymentMessage,
                    paymentService.initiatePayment(
                            "https://app.sandbox.cryptoprocessing.com/api/v2",
                            coinspaidMapper.mapToTransactionRequest(paymentMessage)
                    )
            );
        } catch (final Exception exception){
            response = coinspaidMapper.mapExceptionFromTransactionResponse(paymentMessage, exception.getMessage());
        }

        PaymentResponse finalResponse = response;
        jmsTemplate.send(message.getJMSReplyTo(), session -> Objects.requireNonNull(jmsTemplate.getMessageConverter()).toMessage(finalResponse, session));
    }
}
