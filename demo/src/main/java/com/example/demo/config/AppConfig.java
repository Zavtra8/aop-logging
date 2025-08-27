package com.example.demo.config;

import com.example.demo.interceptor.HeaderForwardingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Autowired
    private HeaderForwardingInterceptor headerForwardingInterceptor;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(headerForwardingInterceptor);
        return restTemplate;
    }
}