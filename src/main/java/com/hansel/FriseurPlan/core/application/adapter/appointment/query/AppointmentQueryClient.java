package com.hansel.FriseurPlan.core.application.adapter.appointment.query;

import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;

public interface AppointmentQueryClient {

    AppointmentReturnDto getAppointmentById(Long id);

}
