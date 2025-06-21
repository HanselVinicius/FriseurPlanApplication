package com.hansel.FriseurPlan.infra.port.output.client.costumer.command;

import com.hansel.FriseurPlan.core.application.adapter.costumer.command.CostumerCommandClient;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.infra.port.output.client.costumer.CostumerMapper;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.costumer.CostumerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CostumerCommandClientImpl implements CostumerCommandClient {

    private final CostumerEntityRepository costumerEntityRepository;

    @Override
    public Costumer createCostumer(Costumer costumer) {
        CostumerEntity costumerEntity = CostumerMapper.toCostumerEntity(costumer);
        return CostumerMapper.toCostumer(this.costumerEntityRepository.save(costumerEntity));
    }
}
