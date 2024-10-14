package com.picpay.desafio.domain.dto;


import lombok.Data;

@Data
public class AuthorizationResponse{
    private String error;
    private String errorCode;

}
