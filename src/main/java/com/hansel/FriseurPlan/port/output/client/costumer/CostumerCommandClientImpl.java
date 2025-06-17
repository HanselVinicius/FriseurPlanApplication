package com.hansel.FriseurPlan.port.output.client.costumer;

import com.hansel.FriseurPlan.core.application.adapter.CostumerCommandClient;
import com.hansel.FriseurPlan.core.domain.costumer.Costumer;
import com.hansel.FriseurPlan.port.output.entities.costumer.CostumerEntity;
import com.hansel.FriseurPlan.port.output.entities.costumer.CostumerEntityRepository;
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
