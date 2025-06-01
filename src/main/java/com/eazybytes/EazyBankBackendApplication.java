package com.eazybytes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
public class EazyBankBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EazyBankBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
		keyGenerator.init(256);
		SecretKey secretKey = keyGenerator.generateKey();
		String base64SecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		System.out.println("Base64 Secret Key: " + base64SecretKey);
	}
}
