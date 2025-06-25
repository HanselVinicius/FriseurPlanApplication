package com.hansel.FriseurPlan.infra.port.output.client.appointment.command;

import com.hansel.FriseurPlan.core.application.adapter.appointment.command.AppointmentCommandClient;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.infra.port.output.client.appointment.AppointmentMapper;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AppointmentCommandClientImpl implements AppointmentCommandClient {

    public final AppointmentEntityRepository appointmentEntityRepository;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        appointment.getHairdresser().setAppointments(new ArrayList<>());
        AppointmentEntity appointmentEntity = AppointmentMapper.toAppointmentEntity(appointment);
        AppointmentEntity save = appointmentEntityRepository.save(appointmentEntity);
        return AppointmentMapper.toSimpleAppointment(save);
    }
}
