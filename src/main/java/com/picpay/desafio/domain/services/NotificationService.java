package com.picpay.desafio.domain.services;

import com.picpay.desafio.domain.client.NotificationClient;
import com.picpay.desafio.domain.dto.NotificationResponse;
import com.picpay.desafio.domain.dto.TransferDto;
import com.picpay.desafio.domain.exception.NotificationFailedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationClient notificationClient;


    public Mono<NotificationResponse> sendNotification(TransferDto transferDto){
        logger.info("Sending notification to Notification Client.");

        return notificationClient.sendNotification(transferDto)
            .doOnError(error  ->  logger.error("Something went wrong while sending to client."))
            .onErrorMap(e -> new NotificationFailedException());
    }
}
