package com.picpay.desafio.domain.dto;

import com.picpay.desafio.domain.entity.UserType;

import java.math.BigDecimal;


public record UserDto(String fullName,
                      String email,
                      int document,
                      String pass,
                      BigDecimal balance,
                      UserType userType) {
}
