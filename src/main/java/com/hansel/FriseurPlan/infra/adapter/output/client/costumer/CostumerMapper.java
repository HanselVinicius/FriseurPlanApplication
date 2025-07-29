package com.hansel.FriseurPlan.infra.adapter.output.client.costumer;

import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.infra.adapter.output.entities.EmailVo;
import com.hansel.FriseurPlan.infra.adapter.output.entities.costumer.CostumerEntity;

public class CostumerMapper {

    public static CostumerEntity toCostumerEntity(Costumer costumer) {
        return CostumerEntity.builder()
                .id(costumer.getId())
                .name(costumer.getName())
                .phoneNumber(costumer.getPhoneNumber())
                .email(EmailVo.fromEmailDomain(costumer.getEmail()))
                .build();
    }

    public static Costumer toCostumerDomain(CostumerEntity costumerEntity) {
        return Costumer.create(
                costumerEntity.getId(),
                costumerEntity.getName(),
                costumerEntity.getPhoneNumber().getNumber(),
                costumerEntity.getEmail().toEmailDomain()
        );
    }
}
