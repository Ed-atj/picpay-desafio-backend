package com.picpay.desafio.domain.services;

import com.picpay.desafio.domain.dto.AuthorizationResponse;
import com.picpay.desafio.domain.dto.TransferDto;
import com.picpay.desafio.domain.exception.AuthorizationFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final WebClient authorizationWebClient;


    public boolean isAuthorized(TransferDto transferDto){
        var response = authorizationWebClient.get()
            .uri("/authorize")
            .retrieve()
            .onStatus(HttpStatusCode::isError, clientResponse -> Mono.error(new AuthorizationFailedException()))
            .bodyToMono(AuthorizationResponse.class)
            .block();

        return response!= null && response.isSuccess();
    }
}
