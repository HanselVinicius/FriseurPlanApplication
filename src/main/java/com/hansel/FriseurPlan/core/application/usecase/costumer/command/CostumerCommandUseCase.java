package com.hansel.FriseurPlan.core.application.usecase.costumer.command;

import com.hansel.FriseurPlan.core.application.adapter.costumer.command.CostumerCommandClient;
import com.hansel.FriseurPlan.core.application.usecase.costumer.query.CostumerQueryUseCase;
import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.application.usecase.dto.CostumerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumerCommandUseCase {

    private final CostumerCommandClient costumerCommandClient;
    private final CostumerQueryUseCase costumerQueryUseCase;

    public Costumer createCostumer(CostumerDto costumerDto,Email email) {
        Costumer costumer = Costumer.create(null, costumerDto.name(), costumerDto.phoneNumber(),email);
        if (this.costumerQueryUseCase.getCostumerByEmail(email) != null) {
            throw new IllegalArgumentException("Costumer with this email already exists");
        }
        return this.costumerCommandClient.createCostumer(costumer);
    }

}
