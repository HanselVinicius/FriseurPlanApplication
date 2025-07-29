package com.hansel.FriseurPlan.core.application.port.appointment.command;

import com.hansel.FriseurPlan.core.domain.appointment.Appointment;

public interface AppointmentCommandClient {

    Appointment createAppointment(Appointment appointment);

    void deleteAppointment(Long id);
}
