package com.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtUtilConfig {

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}