package com.hansel.FriseurPlan.infra.adapter.output.client.appointment.command;

import com.hansel.FriseurPlan.core.application.port.appointment.command.AppointmentCommandClient;
import com.hansel.FriseurPlan.core.domain.appointment.Appointment;
import com.hansel.FriseurPlan.infra.adapter.output.client.appointment.AppointmentMapper;
import com.hansel.FriseurPlan.infra.adapter.output.entities.appointment.AppointmentEntity;
import com.hansel.FriseurPlan.infra.adapter.output.entities.appointment.AppointmentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentCommandClientImpl implements AppointmentCommandClient {

    public final AppointmentEntityRepository appointmentEntityRepository;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        AppointmentEntity appointmentEntity = AppointmentMapper.toAppointmentEntity(appointment);
        AppointmentEntity entitySaved = appointmentEntityRepository.save(appointmentEntity);
        return AppointmentMapper.toSimpleAppointment(entitySaved);
    }

    @Override
    public void deleteAppointment(Long id) {
        this.appointmentEntityRepository.deleteAppointment(id, LocalDateTime.now());
    }
}
