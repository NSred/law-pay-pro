package rs.wepublishlaws.cryptoservice.adapter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import rs.wepublishlaws.cryptoservice.domain.configuration.HttpConfig;

@Component
@ConfigurationProperties("http")
public class HttpConfiguration implements HttpConfig {

    private String paymentNotifyUrl;
    @Override
    public String paymentNotifyUrl() {
        return paymentNotifyUrl;
    }

    public void setPaymentNotifyUrl(final String paymentNotifyUrl) {
        this.paymentNotifyUrl = paymentNotifyUrl;
    }
}
