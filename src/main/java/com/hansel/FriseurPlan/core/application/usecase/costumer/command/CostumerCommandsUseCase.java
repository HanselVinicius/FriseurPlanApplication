package com.hansel.FriseurPlan.core.application.usecase.costumer.command;

import com.hansel.FriseurPlan.core.application.adapter.CostumerCommandClient;
import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.application.usecase.dto.CostumerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumerCommandsUseCase {

    private final CostumerCommandClient costumerCommandClient;

    public Costumer createCostumer(CostumerDto costumerDto,Email email) {
        Costumer costumer = Costumer.create(null, costumerDto.name(), costumerDto.phoneNumber(),email);
        return this.costumerCommandClient.createCostumer(costumer);
    }

}
