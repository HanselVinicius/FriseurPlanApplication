package com.hansel.FriseurPlan.infra.port.output.client.costumer.query;

import com.hansel.FriseurPlan.core.application.adapter.costumer.query.CostumerQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.costumer.dto.CostumerReturnDto;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CostumerQueryClientImpl implements CostumerQueryClient {

    private final CostumerEntityRepository costumerEntityRepository;

    @Override
    public CostumerReturnDto getCostumerByEmail(Email email) {
        Optional<CostumerEntity> costumerEntity = this.costumerEntityRepository.getCostumerEntityByEmail(email.getEmail());
        return costumerEntity.map(CostumerReturnDto::fromEntity).orElse(null);
    }

    @Override
    public CostumerReturnDto getCostumerById(Long id) {
        Optional<CostumerEntity> costumerEntity = this.costumerEntityRepository.findById(id);
        return costumerEntity.map(CostumerReturnDto::fromEntity).orElse(null);
    }
}
