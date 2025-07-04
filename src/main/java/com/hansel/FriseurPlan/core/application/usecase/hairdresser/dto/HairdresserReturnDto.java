package com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto;

import com.hansel.FriseurPlan.core.application.usecase.appointment.dto.AppointmentReturnDto;
import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;

import java.util.List;

public record HairdresserReturnDto(
        Long id,
        String name,
        String phoneNumber,
        Email email,
        Address address,
        List<AppointmentReturnDto> appointmentReturnDto
) {

    public static HairdresserReturnDto fromEntity(HairdresserEntity hairdresserEntity) {
        List<AppointmentReturnDto> appointmentReturnDto = hairdresserEntity.getAppointments().stream().map(AppointmentReturnDto::fromEntitySimple).toList();
        return new HairdresserReturnDto(
                hairdresserEntity.getId(),
                hairdresserEntity.getName(),
                hairdresserEntity.getPhoneNumber().getNumber(),
                Email.create(hairdresserEntity.getEmail().getEmail(),hairdresserEntity.getEmail().isVerified()),
                Address.create(
                        hairdresserEntity.getAddress().getStreet(),
                        hairdresserEntity.getAddress().getNumber(),
                        hairdresserEntity.getAddress().getCity(),
                        hairdresserEntity.getAddress().getState(),
                        hairdresserEntity.getAddress().getZipCode()
                ),
                appointmentReturnDto
        );
    }

    public static HairdresserReturnDto fromDomain(Hairdresser hairdresser) {
        List<AppointmentReturnDto> appointmentReturnDto = hairdresser.getAppointments().stream().map(hairdresserDomain -> new AppointmentReturnDto(
                hairdresserDomain.getId(),
                hairdresserDomain.getTimeRange(),
                null,
                null
        )).toList();
        return new HairdresserReturnDto(
                hairdresser.getId(),
                hairdresser.getName(),
                hairdresser.getPhoneNumber().getNumber(),
                hairdresser.getEmail(),
                hairdresser.getAddress(),
                appointmentReturnDto
        );
    }

    public Hairdresser toDomain() {
        return Hairdresser.create(
                id,
                name,
                null,
                PhoneNumber.create(phoneNumber),
                email,
                address
        );
    }

    public static HairdresserReturnDto fromEntitySimple(HairdresserEntity hairdresserEntity) {
        return new HairdresserReturnDto(
                hairdresserEntity.getId(),
                hairdresserEntity.getName(),
                hairdresserEntity.getPhoneNumber().getNumber(),
                Email.create(hairdresserEntity.getEmail().getEmail(),hairdresserEntity.getEmail().isVerified()),
                Address.create(
                        hairdresserEntity.getAddress().getStreet(),
                        hairdresserEntity.getAddress().getNumber(),
                        hairdresserEntity.getAddress().getCity(),
                        hairdresserEntity.getAddress().getState(),
                        hairdresserEntity.getAddress().getZipCode()
                ),
                null
        );
    }
}
