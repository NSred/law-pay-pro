package com.lawagency.lawly.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {
    private static final String AUDIENCE_WEB = "web";
    @Value("lawly")
    private String APP_NAME;
    @Value("somesecret")
    public String SECRET;
    @Value("1800000")
    private int EXPIRES_IN;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generate(String username) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setAudience(generateAudience())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
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
