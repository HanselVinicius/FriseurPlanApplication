package com.hansel.FriseurPlan.core.application.adapter.appointment.query;

import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.GetAppointmentDto;
import org.springframework.data.domain.Page;


public interface AppointmentQueryClient {

    AppointmentReturnDto getAppointmentById(Long id);

    Page<AppointmentReturnDto> getAppointments(GetAppointmentDto getAppointmentDto);

}
