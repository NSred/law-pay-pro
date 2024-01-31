package com.example.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(BankApplication.class, args);

//		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//		keyGen.init(256, new SecureRandom());
//		SecretKey secretKey = keyGen.generateKey();
//
//		// Encode the secret key as a base64 string for storage
//		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//		System.out.println("Encoded Secret Key: " + encodedKey);
	}

}
