package com.hansel.FriseurPlan.infra.port.output.client.appointment.query;

import com.hansel.FriseurPlan.core.application.adapter.appointment.query.AppointmentQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.GetAppointmentDto;
import com.hansel.FriseurPlan.infra.port.output.client.appointment.specifications.GetAppointmentByRelationSpecs;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentQueryClientImpl implements AppointmentQueryClient {

    private final AppointmentEntityRepository appointmentEntityRepository;

    @Override
    public AppointmentReturnDto getAppointmentById(Long id) {
        Optional<AppointmentEntity> appointmentEntity = this.appointmentEntityRepository.findById(id);
        return appointmentEntity.map(AppointmentReturnDto::fromEntity).orElse(null);
    }

    @Override
    public Page<AppointmentReturnDto> getAppointments(GetAppointmentDto getAppointmentDto) {
        GetAppointmentByRelationSpecs getAppointmentByRelationSpecs = new GetAppointmentByRelationSpecs(
                getAppointmentDto.hairdresserId(),
                getAppointmentDto.costumerId()
        );

        return this.appointmentEntityRepository
                .findAll(getAppointmentByRelationSpecs, getAppointmentDto.pageable())
                .map(AppointmentReturnDto::fromEntity);
    }
}
