package com.hansel.FriseurPlan.infra.port.output.client.costumer.query;

import com.hansel.FriseurPlan.core.application.adapter.costumer.query.CostumerQueryClient;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.infra.port.output.client.costumer.CostumerMapper;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CostumerQueryClientImpl implements CostumerQueryClient {

    private final CostumerEntityRepository costumerEntityRepository;

    @Override
    public Costumer getCostumerByEmail(Email email) {
        var costumerEntity = this.costumerEntityRepository.getCostumerEntityByEmail(email.getEmail());
        return costumerEntity.toCostumerDomain();
    }

    @Override
    public Costumer getCostumerById(Long id) {
        CostumerEntity costumerEntity = this.costumerEntityRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return CostumerMapper.toCostumer(costumerEntity);
    }
}
