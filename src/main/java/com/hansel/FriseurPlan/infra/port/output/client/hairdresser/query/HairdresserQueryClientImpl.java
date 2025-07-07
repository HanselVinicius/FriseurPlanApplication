package com.hansel.FriseurPlan.infra.port.output.client.hairdresser.query;

import com.hansel.FriseurPlan.core.application.adapter.hairdresser.query.HairdresserQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.GetHairdresserDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.infra.port.output.client.hairdresser.specifications.GetHairdresserByParamsSpec;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntity;
import com.hansel.FriseurPlan.infra.port.output.entities.hairdresser.HairdresserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HairdresserQueryClientImpl implements HairdresserQueryClient {

    private final HairdresserEntityRepository hairdresserEntityRepository;

    @Override
    public HairdresserReturnDto getHairdresserByEmail(Email email) {
        Optional<HairdresserEntity> hairdresserEntity = this.hairdresserEntityRepository.getHairdresserEntityByEmail(email.getEmail());
        return hairdresserEntity.map(HairdresserReturnDto::fromEntity).orElse(null);
    }

    @Override
    public HairdresserReturnDto getHairdresserById(Long id) {
        Optional<HairdresserEntity> hairdresserEntity = this.hairdresserEntityRepository.findById(id);
        return hairdresserEntity.map(HairdresserReturnDto::fromEntity).orElse(null);
    }

    @Override
    public Page<HairdresserReturnDto> getAllHairdressers(GetHairdresserDto getHairdresserDto) {
        GetHairdresserByParamsSpec getHairdresserByParamsSpec = new GetHairdresserByParamsSpec(getHairdresserDto.name());

        return this.hairdresserEntityRepository.findAll(
                getHairdresserByParamsSpec, getHairdresserDto.pageable()
        ).map(HairdresserReturnDto::fromEntity);
    }


}
