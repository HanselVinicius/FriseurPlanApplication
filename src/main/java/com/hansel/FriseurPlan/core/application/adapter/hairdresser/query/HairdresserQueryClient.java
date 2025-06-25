package com.hansel.FriseurPlan.core.application.adapter.hairdresser.query;

import com.hansel.FriseurPlan.core.application.usecase.hairdresser.dto.HairdresserReturnDto;
import com.hansel.FriseurPlan.core.domain.email.Email;
import com.hansel.FriseurPlan.core.domain.hairdresser.Hairdresser;

public interface HairdresserQueryClient {

    HairdresserReturnDto getHairdresserByEmail(Email email);

    HairdresserReturnDto getHairdresserById(Long id);

}
