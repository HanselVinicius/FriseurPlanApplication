package com.hansel.FriseurPlan.port.output.client.costumer;

import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.port.output.entities.costumer.CostumerEntity;

public class CostumerMapper {

    public static CostumerEntity toCostumerEntity(Costumer costumer) {
        return CostumerEntity.builder()
                .id(costumer.getId())
                .name(costumer.getName())
                .phoneNumber(costumer.getPhoneNumber())
                .build();
    }

    public static Costumer toCostumer(CostumerEntity costumerEntity) {
        return Costumer.create(
                costumerEntity.getId(),
                costumerEntity.getName(),
                costumerEntity.getPhoneNumber().getNumber()
        );
    }
}
