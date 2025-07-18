package com.hansel.FriseurPlan.core.application.usecase.appointment.dto;

import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntity;

public record AppointmentReturnDto(
        Long id,
        TimeRange timeRange,
        CostumerReturnDto costumer,
        HairdresserReturnDto hairdresser
) {
    public static AppointmentReturnDto fromEntity(AppointmentEntity appointmentEntity) {
        return new AppointmentReturnDto(
                appointmentEntity.getId(),
                appointmentEntity.getTimeRangeVo().toTimeRangeDomain(),
                CostumerReturnDto.fromEntity(appointmentEntity.getCostumerEntity()),
                HairdresserReturnDto.fromEntitySimple(appointmentEntity.getHairdresserEntity())
        );
    }

    public static AppointmentReturnDto fromEntitySimple(AppointmentEntity appointmentEntity) {
        return new AppointmentReturnDto(
                appointmentEntity.getId(),
                appointmentEntity.getTimeRangeVo().toTimeRangeDomain(),
                null,
                null
        );
    }

    public Appointment toDomainSimple(){
        return Appointment.create(
                null,
                timeRange,
                null,
                null
        );
    }


}
