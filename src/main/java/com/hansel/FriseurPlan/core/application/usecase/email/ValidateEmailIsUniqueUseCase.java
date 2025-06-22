package com.hansel.FriseurPlan.core.application.usecase.email;

import com.hansel.FriseurPlan.core.application.usecase.costumer.query.CostumerQueryUseCase;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.query.HairdresserQueryUseCase;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.email.ValidateEmailIsUniqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateEmailIsUniqueUseCase implements ValidateEmailIsUniqueService {

    private final CostumerQueryUseCase costumerQueryUseCase;
    private final HairdresserQueryUseCase hairdresserQueryUseCase;

    @Override
    public boolean validate(Email email) {
        if(costumerQueryUseCase.getCostumerByEmail(email) != null)
            return false;

        return hairdresserQueryUseCase.getHairdresserByEmail(email) == null;
    }
}
