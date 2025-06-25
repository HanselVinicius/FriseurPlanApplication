package com.hansel.FriseurPlan.infra.port.output.client.hairdresser.query;

import com.hansel.FriseurPlan.core.application.adapter.hairdresser.query.HairdresserQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.domain.email.Email;
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
    public HairdresserReturnDto getHairdresserByEmail(Email email) {
        HairdresserEntity hairdresserEntity = this.hairdresserEntityRepository.getHairdresserEntityByEmail(email.getEmail());
        return HairdresserReturnDto.fromEntity((hairdresserEntity));
    }

    @Override
    public HairdresserReturnDto getHairdresserById(Long id) {
        HairdresserEntity hairdresserEntity = this.hairdresserEntityRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return HairdresserReturnDto.fromEntity((hairdresserEntity));
    }


}
