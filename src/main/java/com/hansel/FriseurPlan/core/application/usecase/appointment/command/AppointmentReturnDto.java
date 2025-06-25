package com.hansel.FriseurPlan.core.application.usecase.appointment.command;

import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;

public record AppointmentReturnDto(
        Long id,
        String timeRange,
        CostumerReturnDto costumer,
        HairdresserReturnDto hairdresser
) {
}
