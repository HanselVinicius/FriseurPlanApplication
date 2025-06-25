package com.hansel.FriseurPlan.core.application.usecase.appointment.dto;

import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import jakarta.validation.constraints.NotNull;

public record AppointmentDto(
        @NotNull
        TimeRange timeRange,
        @NotNull
        Long costumerId,
        @NotNull
        Long hairdresserId
) {
}
