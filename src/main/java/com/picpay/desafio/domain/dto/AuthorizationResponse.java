package com.picpay.desafio.domain.dto;

import lombok.Data;

@Data
public class AuthorizationResponse {

    private boolean success;
    private String message;
    private String errorCode;
}
