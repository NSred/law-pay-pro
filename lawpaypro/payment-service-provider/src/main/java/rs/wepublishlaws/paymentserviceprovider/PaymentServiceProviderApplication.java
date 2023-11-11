package rs.wepublishlaws.paymentserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PaymentServiceProviderApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PaymentServiceProviderApplication.class, args);

        Producer producer = context.getBean(Producer.class);

        // Define the destination and message you want to send
        String destination = "TESTIRANJE"; // Replace with your destination queue or topic name
        String message = "Hello, this is a test message.";

        // Send the message
        producer.sendMessage(destination, message);
    }
}