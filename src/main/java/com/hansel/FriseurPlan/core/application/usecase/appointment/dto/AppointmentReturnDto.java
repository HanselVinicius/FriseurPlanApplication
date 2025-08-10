package com.hansel.FriseurPlan.core.application.usecase.appointment.dto;

import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import com.hansel.FriseurPlan.infra.adapter.output.entities.appointment.AppointmentEntity;

public record AppointmentReturnDto(
        Long id,
        TimeRange timeRange,
        Long costumerId,
        Long hairdresserId
) {
    public static AppointmentReturnDto fromEntity(AppointmentEntity appointmentEntity) {
        return new AppointmentReturnDto(
                appointmentEntity.getId(),
                appointmentEntity.getTimeRangeVo().toTimeRangeDomain(),
                appointmentEntity.getCostumerEntity().getId(),
                appointmentEntity.getHairdresserEntity().getId()
        );
    }

    public static AppointmentReturnDto fromEntitySimple(AppointmentEntity appointmentEntity) {
        return new AppointmentReturnDto(
                appointmentEntity.getId(),
                appointmentEntity.getTimeRangeVo().toTimeRangeDomain(),
                appointmentEntity.getCostumerEntity().getId(),
                appointmentEntity.getId()
        );
    }

    public Appointment toDomainSimple() {
        return Appointment.create(
                null,
                timeRange,
                null,
                null
        );
    }


}
