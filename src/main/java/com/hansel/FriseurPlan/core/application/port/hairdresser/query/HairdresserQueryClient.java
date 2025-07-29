package com.hansel.FriseurPlan.core.application.port.hairdresser.query;

import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.GetHairdresserDto;
import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.domain.email.Email;
import org.springframework.data.domain.Page;

public interface HairdresserQueryClient {

    HairdresserReturnDto getHairdresserByEmail(Email email);

    HairdresserReturnDto getHairdresserById(Long id);

    Page<HairdresserReturnDto> getAllHairdressers(GetHairdresserDto getHairdresserDto);

}
