package com.hansel.FriseurPlan.core.application.adapter.costumer.query;

import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.domain.email.Email;

public interface CostumerQueryClient {

    CostumerReturnDto getCostumerByEmail(Email email);

    CostumerReturnDto getCostumerById(Long id);
}
