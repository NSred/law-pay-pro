package rs.wepublishlaws.cryptoservice.adapter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import rs.wepublishlaws.cryptoservice.domain.configuration.QRCodeConfig;

@Component
@ConfigurationProperties("qr-code")
public class QRCodeConfiguration implements QRCodeConfig {

    private int width;
    private int height;

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }
}
