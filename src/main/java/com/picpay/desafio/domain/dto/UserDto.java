package com.picpay.desafio.domain.dto;

import com.picpay.desafio.domain.entity.UserType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record UserDto(@NotNull String fullName,
                      @NotNull String email,
                      @NotNull int document,
                      @NotNull String pass,
                      @NotNull BigDecimal balance,
                      @NotNull UserType userType) {
}
