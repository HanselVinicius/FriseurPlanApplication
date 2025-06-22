package com.hansel.FriseurPlan.core.application.adapter.costumer.query;

import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;

public interface CostumerQueryClient {

    Costumer getCostumerByEmail(Email email);

    Costumer getCostumerById(Long id);
}
