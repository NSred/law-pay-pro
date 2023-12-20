package rs.wepublishlaws.paymentserviceprovider.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;

import java.util.Date;

@Component
public class TokenGenerator {
    private static final String AUDIENCE_WEB = "web";
    @Value("lawpaypro")
    private String APP_NAME;
    @Value("somesecret")
    public String SECRET;
    @Value("1800000")
    private int EXPIRES_IN;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generate(Merchant user) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(user.getMerchantId())
                .setAudience(generateAudience())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .claim("id", user.getId())
                .claim("merchant", user.getMerchantId())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    private String generateAudience() {
        return AUDIENCE_WEB;
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + EXPIRES_IN);
    }

    public int getExpiredIn() {
        return EXPIRES_IN;
    }
}
