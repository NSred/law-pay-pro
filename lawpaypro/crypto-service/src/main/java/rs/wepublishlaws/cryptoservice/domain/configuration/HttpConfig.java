package rs.wepublishlaws.cryptoservice.domain.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


public interface HttpConfig{

    public String paymentNotifyUrl();
}
