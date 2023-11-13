package rs.wepublishlaws.qrcodeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"rs.wepublishlaws.buildingblocks", "rs.wepublishlaws.qrcodeservice"})
public class QrCodeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(QrCodeServiceApplication.class, args);
    }
}