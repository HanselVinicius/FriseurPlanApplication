package com.hansel.FriseurPlan.core.application.adapter.appointment.command;

import com.hansel.FriseurPlan.core.domain.appointment.Appointment;

public interface AppointmentCommandClient {

    Appointment createAppointment(Appointment appointment);

    void deleteAppointment(Long id);
}
