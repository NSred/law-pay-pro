package rs.wepublishlaws.paypalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"rs.wepublishlaws.buildingblocks", "rs.wepublishlaws.paypalservice"})
public class PayPalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayPalServiceApplication.class, args);
    }
}