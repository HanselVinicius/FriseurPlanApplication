package com.hansel.FriseurPlan.core.application.usecase.appointment.query;

import com.hansel.FriseurPlan.core.application.adapter.appointment.query.AppointmentQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.GetAppointmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AppointmentQueryUseCase {

    private final AppointmentQueryClient appointmentQueryClient;

    public AppointmentReturnDto getAppointmentById(Long id){
        return appointmentQueryClient.getAppointmentById(id);
    }

    public Page<AppointmentReturnDto> getAppointments(GetAppointmentDto  getAppointmentDto){
        return this.appointmentQueryClient.getAppointments(getAppointmentDto);
    }

}
