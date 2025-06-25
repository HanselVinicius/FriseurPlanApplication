package com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto;

import com.hansel.FriseurPlan.core.domain.Address;
import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;

public record HairdresserReturnDto(
        Long id,
        String name,
        String phoneNumber,
        Email email,
        Address address
) {

    public static HairdresserReturnDto fromEntity(HairdresserEntity hairdresserEntity) {
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
                )
        );
    }

    public static HairdresserReturnDto fromDomain(Hairdresser hairdresser) {
        return new HairdresserReturnDto(
                hairdresser.getId(),
                hairdresser.getName(),
                hairdresser.getPhoneNumber().getNumber(),
                hairdresser.getEmail(),
                hairdresser.getAddress()
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
}
