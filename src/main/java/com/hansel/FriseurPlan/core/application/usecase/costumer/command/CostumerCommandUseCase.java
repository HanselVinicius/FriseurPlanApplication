package com.hansel.FriseurPlan.core.application.usecase.costumer.command;

import com.hansel.FriseurPlan.core.application.port.costumer.command.CostumerCommandClient;
import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerDto;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.email.ValidateEmailIsUniqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumerCommandUseCase {

    private final CostumerCommandClient costumerCommandClient;
    private final ValidateEmailIsUniqueService validateEmailIsUniqueService;

    public Costumer createCostumer(CostumerDto costumerDto,Email email) {
        Costumer costumer = Costumer.create(null, costumerDto.name(), costumerDto.phoneNumber(),email);
        if(!email.validateEmailIsUnique(validateEmailIsUniqueService)){
            throw new IllegalArgumentException("Specified email is already in use.");
        }
        return this.costumerCommandClient.createCostumer(costumer);
    }

}
