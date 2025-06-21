package com.hansel.FriseurPlan.core.application.usecase.hairdresser.command;

import com.hansel.FriseurPlan.core.application.adapter.HairdresserCommandClient;
import com.hansel.FriseurPlan.core.application.usecase.dto.HairdresserDto;
import com.hansel.FriseurPlan.core.domain.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class HairdresserCommandUseCase {

    private final HairdresserCommandClient hairdresserCommandClient;

    public Hairdresser createHairdresser(HairdresserDto hairdresserDto, Email email) {
        Hairdresser hairdresser = Hairdresser.create(null,
                hairdresserDto.name(),
                new ArrayList<>(),
                hairdresserDto.phoneNumber(),
                email,
                hairdresserDto.address());

        return hairdresserCommandClient.createHairdresser(hairdresser);
    }


}
