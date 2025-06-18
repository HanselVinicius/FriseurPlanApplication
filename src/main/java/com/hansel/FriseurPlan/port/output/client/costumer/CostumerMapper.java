package com.hansel.FriseurPlan.port.output.client.costumer;

import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.port.output.entities.EmailVo;
import com.hansel.FriseurPlan.port.output.entities.costumer.CostumerEntity;

public class CostumerMapper {

    public static CostumerEntity toCostumerEntity(Costumer costumer) {
        EmailVo emailVo = new EmailVo(costumer.getEmail().getEmail(), costumer.getEmail().isVerified());
        return CostumerEntity.builder()
                .id(costumer.getId())
                .name(costumer.getName())
                .phoneNumber(costumer.getPhoneNumber())
                .email(emailVo)
                .build();
    }

    public static Costumer toCostumer(CostumerEntity costumerEntity) {
        Email email = Email.create(
                costumerEntity.getEmail().getEmail(),
                costumerEntity.getEmail().isVerified()
        );
        return Costumer.create(
                costumerEntity.getId(),
                costumerEntity.getName(),
                costumerEntity.getPhoneNumber().getNumber(),
                email
        );
    }
}
