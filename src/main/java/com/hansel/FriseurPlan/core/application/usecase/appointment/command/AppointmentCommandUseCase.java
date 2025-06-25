package com.hansel.FriseurPlan.core.application.usecase.appointment.command;

import com.hansel.FriseurPlan.core.application.adapter.appointment.command.AppointmentCommandClient;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentDto;
import com.hansel.FriseurPlan.core.application.usecase.costumer.query.CostumerQueryUseCase;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.query.HairdresserQueryUseCase;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentCommandUseCase {

    private final CostumerQueryUseCase costumerQueryUseCase;
    private final HairdresserQueryUseCase hairdresserQueryUseCase;
    private final AppointmentCommandClient appointmentCommandClient;

    public Appointment createAppointment(AppointmentDto appointmentDto) {
        Costumer costumerById = this.costumerQueryUseCase.getCostumerById(appointmentDto.costumerId());
        Hairdresser hairdresserById = this.hairdresserQueryUseCase.getHairdresserById(appointmentDto.hairdresserId()).toDomain();
        Appointment appointment = Appointment.create(
                null,
                appointmentDto.timeRange(),
                costumerById,
                hairdresserById
        );
        return appointmentCommandClient.createAppointment(appointment);
    }
}
