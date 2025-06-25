package com.hansel.FriseurPlan.core.application.usecase.costumer.dto;


import jakarta.validation.constraints.NotBlank;

public record CostumerDto(
    @NotBlank
    String name,
    @NotBlank
    String phoneNumber
) {
}
