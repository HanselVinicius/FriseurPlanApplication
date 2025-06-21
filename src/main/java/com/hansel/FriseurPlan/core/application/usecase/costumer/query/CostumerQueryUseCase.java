package com.hansel.FriseurPlan.core.application.usecase.costumer.query;

import com.hansel.FriseurPlan.core.application.adapter.costumer.query.CostumerQueryClient;
import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumerQueryUseCase {

    private final CostumerQueryClient costumerQueryClient;

    public Costumer getCostumerByEmail(Email email) {
        return costumerQueryClient.getCostumerByEmail(email);
    }

    public Costumer getCostumerById(Long id) {
        return this.costumerQueryClient.getCostumerById(id);
    }
}
