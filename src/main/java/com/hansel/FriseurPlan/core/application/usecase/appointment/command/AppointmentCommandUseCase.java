package com.hansel.FriseurPlan.core.application.usecase.appointment.command;

import com.hansel.FriseurPlan.core.application.adapter.appointment.command.AppointmentCommandClient;
import com.hansel.FriseurPlan.core.application.adapter.appointment.query.AppointmentQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.costumer.query.CostumerQueryUseCase;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.query.HairdresserQueryUseCase;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentCommandUseCase {

    private final CostumerQueryUseCase costumerQueryUseCase;
    private final HairdresserQueryUseCase hairdresserQueryUseCase;
    private final AppointmentCommandClient appointmentCommandClient;
    private final AppointmentQueryClient appointmentQueryClient;

    public Appointment createAppointment(AppointmentDto appointmentDto) {
        CostumerReturnDto costumerById = this.costumerQueryUseCase.getCostumerById(appointmentDto.costumerId());
        HairdresserReturnDto hairdresserById = this.hairdresserQueryUseCase.getHairdresserById(appointmentDto.hairdresserId());
        if(costumerById == null || hairdresserById == null){
            throw new IllegalArgumentException("costumerId and HairdresserId can not be null");
        }
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
        List<Appointment> appointmentReturnDto = hairdresserById.appointmentReturnDto().stream().map(AppointmentReturnDto::toDomainSimple).collect(Collectors.toList());
        appointmentReturnDto.add(appointment);
        appointmentReturnDto.forEach(hairdresserDomain::addAppointment);
    }

    public void deleteAppointmentById(Long id) {
        AppointmentReturnDto appointmentById = appointmentQueryClient.getAppointmentById(id);
        if (appointmentById == null) {
            throw new IllegalArgumentException("appointment with id " +  id + " does not exist");
        }
        this.appointmentCommandClient.deleteAppointment(appointmentById.id());
    }
}
