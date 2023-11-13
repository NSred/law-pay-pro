package rs.wepublishlaws.cardpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"rs.wepublishlaws.buildingblocks", "rs.wepublishlaws.cardpaymentservice"})
public class CardPaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardPaymentServiceApplication.class, args);
    }
}