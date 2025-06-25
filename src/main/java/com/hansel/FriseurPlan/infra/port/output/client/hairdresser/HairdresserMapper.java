package com.hansel.FriseurPlan.infra.port.output.client.hairdresser;

import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.port.output.client.appointment.AppointmentMapper;
import com.hansel.FriseurPlan.infra.port.output.entities.AddressVo;
import com.hansel.FriseurPlan.infra.port.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.port.output.entities.appointment.AppointmentEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;

import java.util.stream.Collectors;

public class HairdresserMapper {

    public static HairdresserEntity toEntity(Hairdresser hairdresser) {
        if (hairdresser == null) {
            return null;
        }

        return HairdresserEntity.builder()
                .id(hairdresser.getId())
                .name(hairdresser.getName())
                .email(EmailVo.fromEmailDomain(hairdresser.getEmail()))
                .address(AddressVo.fromAddressDomain(hairdresser.getAddress()))
                .phoneNumber(PhoneNumber.create(hairdresser.getPhoneNumber().getNumber()))
                .build();
    }

    public static Hairdresser toDomain(HairdresserEntity entity) {
        if (entity == null) {
            return null;
        }

        PhoneNumber phoneNumber = PhoneNumber.create(entity.getPhoneNumber().getNumber());

        return Hairdresser.create(
                entity.getId(),
                entity.getName(),
                entity.getAppointments().stream().map(AppointmentEntity::toAppointmentDomain).collect(Collectors.toList()),
                phoneNumber,
                entity.getEmail().toEmailDomain(),
                entity.getAddress().toAddressDomain()
        );
    }

}
