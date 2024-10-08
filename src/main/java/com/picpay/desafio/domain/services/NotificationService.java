package com.picpay.desafio.domain.services;

import com.picpay.desafio.domain.dto.NotificationResponse;
import com.picpay.desafio.domain.dto.TransferDto;
import com.picpay.desafio.domain.exception.NotificationFailedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    public final WebClient notificationWebClient;

    public Mono<NotificationResponse> sendNotification(TransferDto transferDto){
        logger.info("Sending notification...");

        return notificationWebClient.post()
            .uri("/notify")
            .bodyValue(transferDto)
            .retrieve()
            .onStatus(HttpStatusCode::isError, clientResponse -> Mono.error(new NotificationFailedException()))
            .bodyToMono(NotificationResponse.class)
            .doOnSuccess(response -> logger.info("Notification sent successfully."))
            .doOnError(e -> logger.error("Error while sending notification.", e));
    }
}
