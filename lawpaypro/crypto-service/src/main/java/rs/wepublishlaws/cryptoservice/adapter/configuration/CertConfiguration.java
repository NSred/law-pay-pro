package rs.wepublishlaws.cryptoservice.adapter.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import rs.wepublishlaws.cryptoservice.domain.configuration.CertConfig;

@Component
@ConfigurationProperties("cert")
@Setter
public class CertConfiguration implements CertConfig {
    private String publicKey;
    private String privateKey;

    @Override
    public String publicKey() {
        return publicKey;
    }

    @Override
    public String privateKey() {
        return privateKey;
    }
}
