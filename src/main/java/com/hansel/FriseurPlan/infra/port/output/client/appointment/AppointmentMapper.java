package com.hansel.FriseurPlan.infra.port.output.client.appointment;

import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.infra.port.output.client.costumer.CostumerMapper;
import com.hansel.FriseurPlan.infra.port.output.client.hairdresser.HairdresserMapper;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.vo.TimeRangeVo;



public class AppointmentMapper {

    public static AppointmentEntity toAppointmentEntity(Appointment appointment) {
        return AppointmentEntity.builder()
                .id(appointment.getId())
                .timeRangeVo(TimeRangeVo.fromTimeRangeDomain(appointment.getTimeRange()))
                .costumerEntity(CostumerMapper.toCostumerEntity(appointment.getCostumer()))
                .hairdresserEntity(HairdresserMapper.toEntity(appointment.getHairdresser()))
                .build();
    }

    public static Appointment toSimpleAppointment(AppointmentEntity appointmentEntity) {
        return Appointment.create(
                appointmentEntity.getId(),
                appointmentEntity.getTimeRangeVo().toTimeRangeDomain(),
                null,
                null);
    }


}
