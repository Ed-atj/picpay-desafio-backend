package com.picpay.desafio.domain.client;

import com.picpay.desafio.domain.dto.AuthorizationResponse;
import com.picpay.desafio.domain.dto.TransferDto;
import com.picpay.desafio.domain.exception.NotificationFailedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthorizationClient {

    private final Logger logger = LoggerFactory.getLogger(AuthorizationClient.class);
    private final WebClient authorizationWebClient;

    public Mono<AuthorizationResponse> authorize(TransferDto transferDto) {
        return authorizationWebClient.post()
            .uri("/authorize")
            .bodyValue(transferDto)
            .retrieve()
            .onStatus(HttpStatusCode::isError, authClient -> authClient.bodyToMono(Void.class)
                .flatMap(errorResponse -> {
                    logger.error("Failed to sent notification: {}", logger.getName());
                    return Mono.error(new NotificationFailedException());
                }))
            .bodyToMono(AuthorizationResponse.class)
            .doOnSuccess(response -> logger.info("Authorized."))
            .doOnError(e -> logger.error("Error while processing authorization.", e.getCause()));
    }
}
