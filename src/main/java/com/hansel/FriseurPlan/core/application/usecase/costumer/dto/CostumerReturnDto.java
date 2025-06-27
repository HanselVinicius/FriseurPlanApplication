package com.hansel.FriseurPlan.core.application.usecase.costumer.dto;

import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntity;

public record CostumerReturnDto(
    Long id,
    String name,
    PhoneNumber phoneNumber,
    Email email
) {

    public static CostumerReturnDto fromDomain(Costumer costumer) {
        return new CostumerReturnDto(
            costumer.getId(),
            costumer.getName(),
            costumer.getPhoneNumber(),
            costumer.getEmail()
        );
    }

    public static CostumerReturnDto fromEntity(CostumerEntity costumer) {
        return new CostumerReturnDto(
            costumer.getId(),
            costumer.getName(),
            costumer.getPhoneNumber(),
            costumer.getEmail().toEmailDomain()
        );
    }

    public Costumer toDomain() {
        return Costumer.create(
            id,
            name,
            phoneNumber.getNumber(),
            email
        );
    }

}
