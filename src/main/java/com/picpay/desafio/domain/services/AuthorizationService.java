package com.picpay.desafio.domain.services;

import com.picpay.desafio.domain.client.AuthorizationClient;
import com.picpay.desafio.domain.dto.AuthorizationResponse;
import com.picpay.desafio.domain.dto.TransferDto;
import com.picpay.desafio.domain.entity.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;


    public Mono<AuthorizationResponse> authorize(TransferDto transferDto){
        return authorizationClient.authorize(transferDto);
    }
}
