package com.hansel.FriseurPlan.core.usecase;

import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.usecase.dto.CostumerDto;
import org.springframework.stereotype.Service;

@Service
public class CostumerCommandsUseCase {

    public Costumer createCostumer(CostumerDto costumerDto) {
        return Costumer.create(null, costumerDto.name(), costumerDto.phoneNumber());
    }

}
