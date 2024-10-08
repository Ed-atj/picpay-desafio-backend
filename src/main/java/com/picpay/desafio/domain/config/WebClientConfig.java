package com.picpay.desafio.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${authorization-service.url}")
    private String authorizationServiceUrl;

    @Value("${notification-service.url}")
    private String notificationServiceUrl;


    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

    @Bean
    public WebClient authorizationWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder()
            .baseUrl(authorizationServiceUrl)
            .build();
    }

    @Bean
    public WebClient notificationWebClient(WebClient.Builder webClientBuilder){
        return webClientBuilder()
            .baseUrl(notificationServiceUrl)
            .build();
    }
}
