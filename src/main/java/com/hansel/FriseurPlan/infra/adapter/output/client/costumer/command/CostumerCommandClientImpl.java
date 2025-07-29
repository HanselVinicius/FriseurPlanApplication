package com.hansel.FriseurPlan.infra.adapter.output.client.costumer.command;

import com.hansel.FriseurPlan.core.application.port.costumer.command.CostumerCommandClient;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.infra.adapter.output.client.costumer.CostumerMapper;
import com.hansel.FriseurPlan.infra.adapter.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.adapter.output.entities.costumer.CostumerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CostumerCommandClientImpl implements CostumerCommandClient {

    private final CostumerEntityRepository costumerEntityRepository;

    @Override
    public Costumer createCostumer(Costumer costumer) {
        CostumerEntity costumerEntity = CostumerMapper.toCostumerEntity(costumer);
        return CostumerMapper.toCostumerDomain(this.costumerEntityRepository.save(costumerEntity));
    }
}
