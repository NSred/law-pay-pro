package rs.wepublishlaws.paymentserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import rs.wepublishlaws.buildingblocks.ActiveMqProducer;

@SpringBootApplication
@ComponentScan(basePackages = {"rs.wepublishlaws.buildingblocks", "rs.wepublishlaws.paymentserviceprovider"})
public class PaymentServiceProviderApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PaymentServiceProviderApplication.class, args);
    }
}