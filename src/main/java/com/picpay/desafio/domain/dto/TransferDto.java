package com.picpay.desafio.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferDto(@NotNull String id,
                          @NotNull Long sender,
                          @NotNull Long receiver,
                          @NotNull BigDecimal value) {
}
