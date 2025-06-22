package com.hansel.FriseurPlan.infra.port.output.client.hairdresser.query;

import com.hansel.FriseurPlan.core.application.adapter.hairdresser.query.HairdresserQueryClient;
import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import com.hansel.FriseurPlan.infra.port.output.client.hairdresser.HairdresserMapper;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HairdresserQueryClientImpl implements HairdresserQueryClient {

    private final HairdresserEntityRepository hairdresserEntityRepository;

    @Override
    public Hairdresser getHairdresserByEmail(Email email) {
        HairdresserEntity hairdresserEntity = this.hairdresserEntityRepository.getHairdresserEntityByEmail(email.getEmail());
        return HairdresserMapper.toDomain(hairdresserEntity);
    }

    @Override
    public Hairdresser getHairdresserById(Long id) {
        HairdresserEntity hairdresserEntity = this.hairdresserEntityRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return HairdresserMapper.toDomain(hairdresserEntity);
    }


}
