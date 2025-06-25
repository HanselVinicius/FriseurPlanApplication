package com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto;

import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HairdresserDto(
        @NotBlank
        String name,
        @NotNull
        PhoneNumber phoneNumber,
        @NotNull
        Address address
) {

}
