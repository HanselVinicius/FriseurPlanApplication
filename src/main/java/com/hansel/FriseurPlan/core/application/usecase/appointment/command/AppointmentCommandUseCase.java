package com.hansel.FriseurPlan.core.application.usecase.appointment.command;

import com.hansel.FriseurPlan.core.application.adapter.appointment.command.AppointmentCommandClient;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentDto;
import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.costumer.query.CostumerQueryUseCase;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.query.HairdresserQueryUseCase;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentCommandUseCase {

    private final CostumerQueryUseCase costumerQueryUseCase;
    private final HairdresserQueryUseCase hairdresserQueryUseCase;
    private final AppointmentCommandClient appointmentCommandClient;

    public Appointment createAppointment(AppointmentDto appointmentDto) {
        CostumerReturnDto costumerById = this.costumerQueryUseCase.getCostumerById(appointmentDto.costumerId());
        HairdresserReturnDto hairdresserById = this.hairdresserQueryUseCase.getHairdresserById(appointmentDto.hairdresserId());

        Hairdresser hairdresserDomain = hairdresserById.toDomain();
        Appointment appointment = Appointment.create(
                null,
                appointmentDto.timeRange(),
                costumerById.toDomain(),
                hairdresserDomain
        );

        this.addAppointmentToHairdresser(hairdresserById, appointment, hairdresserDomain);
        return appointmentCommandClient.createAppointment(appointment);
    }

    private void addAppointmentToHairdresser(HairdresserReturnDto hairdresserById, Appointment appointment, Hairdresser hairdresserDomain) {
        List<Appointment> appointmentReturnDto = new ArrayList<>(hairdresserById.appointmentReturnDto().stream().map(AppointmentReturnDto::toDomain).toList());
        appointmentReturnDto.add(appointment);
        appointmentReturnDto.forEach(hairdresserDomain::addAppointment);
    }
}
