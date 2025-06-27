package com.hansel.FriseurPlan.core.application.usecase.costumer.query;

import com.hansel.FriseurPlan.core.application.adapter.costumer.query.CostumerQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.domain.email.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumerQueryUseCase {

    private final CostumerQueryClient costumerQueryClient;

    public CostumerReturnDto getCostumerByEmail(Email email) {
        return costumerQueryClient.getCostumerByEmail(email);
    }

    public CostumerReturnDto getCostumerById(Long id) {
        return this.costumerQueryClient.getCostumerById(id);
    }
}
