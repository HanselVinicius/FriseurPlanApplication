package com.hansel.FriseurPlan.core.application.usecase.hairdresser.query;

import com.hansel.FriseurPlan.core.application.adapter.hairdresser.query.HairdresserQueryClient;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.GetHairdresserDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.domain.email.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HairdresserQueryUseCase {

    private final HairdresserQueryClient hairdresserQueryClient;

    public HairdresserReturnDto getHairdresserByEmail(Email email) {
        return this.hairdresserQueryClient.getHairdresserByEmail(email);
    }

    public HairdresserReturnDto getHairdresserById(Long id) {
        return this.hairdresserQueryClient.getHairdresserById(id);
    }

    public Page<HairdresserReturnDto> getAllHairdressers(GetHairdresserDto getHairdresserDto) {
        return this.hairdresserQueryClient.getAllHairdressers(getHairdresserDto);
    }

}
