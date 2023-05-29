package com.lhind.internship.TravelPlans.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfiguration {
  @Bean
  public SecretKey secretKey() {
    return Keys.secretKeyFor(SignatureAlgorithm.HS256);
  }
}
