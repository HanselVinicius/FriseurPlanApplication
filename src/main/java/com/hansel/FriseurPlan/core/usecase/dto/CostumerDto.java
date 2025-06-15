package com.hansel.FriseurPlan.core.usecase.dto;


import jakarta.validation.constraints.NotBlank;

public record CostumerDto(
    @NotBlank
    String name,
    @NotBlank
    String phoneNumber
) {
}
