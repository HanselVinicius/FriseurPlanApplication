package com.hansel.FriseurPlan.core.application.usecase.appointment.dto;

import org.springframework.data.domain.Pageable;

public record GetAppointmentDto(
        Long costumerId,
        Long hairdresserId,
        Pageable pageable
){}
