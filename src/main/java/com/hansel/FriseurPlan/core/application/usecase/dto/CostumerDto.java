package com.hansel.FriseurPlan.core.application.usecase.dto;


import jakarta.validation.constraints.NotBlank;

public record CostumerDto(
    @NotBlank
    String name,
    @NotBlank
    String phoneNumber
) {
}
