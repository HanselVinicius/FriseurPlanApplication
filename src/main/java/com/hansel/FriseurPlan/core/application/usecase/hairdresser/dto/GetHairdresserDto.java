package com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto;


import org.springframework.data.domain.Pageable;

public record GetHairdresserDto(
        Pageable pageable,
        String name
){
}
