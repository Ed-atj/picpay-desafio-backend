package com.picpay.desafio.domain.client;

import com.picpay.desafio.domain.dto.NotificationResponse;
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
public class NotificationClient {


    private final Logger logger = LoggerFactory.getLogger(NotificationClient.class);
    private final WebClient notificationWebClient;

    public Mono<NotificationResponse> sendNotification(TransferDto transferDto){
        return notificationWebClient.post()
            .uri("/send")
            .bodyValue(transferDto)
            .retrieve()
            .onStatus(HttpStatusCode::isError, notifClient -> notifClient.bodyToMono(String.class)
                    .flatMap(errorResponse -> {
                        logger.error("Failed to sent notification: {}", notifClient.statusCode());
                        return Mono.error(new NotificationFailedException());
                    }))
            .bodyToMono(NotificationResponse.class)
            .doOnSuccess(response -> logger.info("Notification sent successfully."))
            .doOnError(e -> logger.info("Notification went wrong", e.getCause()));
    }
}
