package com.hansel.FriseurPlan.core.application.usecase.appointment.query;

import com.hansel.FriseurPlan.core.application.adapter.appointment.query.AppointmentQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentQueryUseCase {

    private final AppointmentQueryClient appointmentQueryClient;

    public AppointmentReturnDto getAppointmentById(Long id){
        return appointmentQueryClient.getAppointmentById(id);
    }

}
