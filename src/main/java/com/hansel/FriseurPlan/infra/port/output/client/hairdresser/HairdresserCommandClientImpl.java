package com.hansel.FriseurPlan.infra.port.output.client.hairdresser;

import com.hansel.FriseurPlan.core.application.adapter.HairdresserCommandClient;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HairdresserCommandClientImpl implements HairdresserCommandClient {

    private final HairdresserEntityRepository hairdresserEntityRepository;

    @Override
    public Hairdresser createHairdresser(Hairdresser hairdresser) {
        HairdresserEntity entity = HairdresserMapper.toEntity(hairdresser);
        HairdresserEntity savedHairdresser = hairdresserEntityRepository.save(entity);
        return HairdresserMapper.toDomain(savedHairdresser);
    }
}
