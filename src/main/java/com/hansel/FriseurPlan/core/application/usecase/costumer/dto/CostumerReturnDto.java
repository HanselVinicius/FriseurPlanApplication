package com.hansel.FriseurPlan.core.application.usecase.costumer.dto;

import com.hansel.FriseurPlan.core.domain.PhoneNumber;
import com.hansel.FriseurPlan.core.domain.email.Email;

public record CostumerReturnDto(
    Long id,
    String name,
    PhoneNumber phoneNumber,
    Email email
) {
}
