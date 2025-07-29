package com.hansel.FriseurPlan.core.application.usecase.hairdresser.command;

import com.hansel.FriseurPlan.core.application.port.hairdresser.command.HairdresserCommandClient;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserDto;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.email.ValidateEmailIsUniqueService;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class HairdresserCommandUseCase {

    private final HairdresserCommandClient hairdresserCommandClient;
    private final ValidateEmailIsUniqueService validateEmailIsUniqueService;

    public Hairdresser createHairdresser(HairdresserDto hairdresserDto, Email email) {
        Hairdresser hairdresser = Hairdresser.create(null,
                hairdresserDto.name(),
                new ArrayList<>(),
                hairdresserDto.phoneNumber(),
                email,
                hairdresserDto.address());

        if (!email.validateEmailIsUnique(validateEmailIsUniqueService)) {
            throw new IllegalArgumentException("Specified email is already in use.");
        }

        return hairdresserCommandClient.createHairdresser(hairdresser);
    }


}
