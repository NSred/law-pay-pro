package rs.wepublishlaws.cryptoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"rs.wepublishlaws.buildingblocks", "rs.wepublishlaws.cryptoservice"})
public class CryptoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptoServiceApplication.class, args);
    }
}